package com.suncreate.handle;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;

import com.cas.login.CaptchaException;
import com.cas.login.PasswordEncryption;
import com.cas.login.UsernamePasswordCaptchaCredential;
import com.suncreate.common.CasConstants;
import com.suncreate.exception.MyAccountNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.apereo.cas.authentication.*;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 自定义用户认证核心代码
 * @author zzg
 *
 */
public class UsernamePasswordCaptchaAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;

     public UsernamePasswordCaptchaAuthenticationHandler(String name, ServicesManager servicesManager,
                                                        PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential)
            throws GeneralSecurityException, PreventedException {
        // 用户凭证
        UsernamePasswordCaptchaCredential myCredential = (UsernamePasswordCaptchaCredential) credential;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String serviceUrl = attributes.getRequest().getParameter("service");
        if(StringUtils.isEmpty(serviceUrl)){
            //服务为空 则给出提示
            throw new MyAccountNotFoundException(CasConstants.ERROR_MESSAGE.LACK_SEVICE);
            //  未认证授权的服务
            //  授权的服务重复
        }
        //截取服务
        String urlPath = "";
        try {
            URL url = new URL(serviceUrl);
            urlPath = url.getProtocol()+"://"+url.getAuthority()+"/";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new FailedLoginException(CasConstants.ERROR_MESSAGE.NO_AUTH_SERVICE);
        }
        int serverCount =  jdbcTemplate.queryForObject("select count(1) from PT_SUB_SYSTEM where sso_key = ?", new Object[]{urlPath}, Integer.class);
        if(serverCount==0){
            throw new CaptchaException(CasConstants.ERROR_MESSAGE.NO_AUTH_SERVICE);
        }
        if(serverCount>1){
            throw new CaptchaException(CasConstants.ERROR_MESSAGE.REPEAT_SERVICE);
        }

        // 查询数据库加密的的密码
        Map<String, Object> user = jdbcTemplate.queryForMap("select * from PT_USER_INFO where user_name = ? AND IS_DEL =0",myCredential.getUsername());
        if (user == null) {
            throw new AccountNotFoundException(CasConstants.ERROR_MESSAGE.ERROR_NAME);
        }

        // 返回多属性
        Map<String, Object> map = new HashMap<>();
        map.put("username", myCredential.getUsername());

        // 密码加密验证(MD5 32位 大写)
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        List<MessageDescriptor>  warning = new ArrayList<MessageDescriptor>();
        if (passwordEncryption.matches(myCredential.getPassword(), user.get("password").toString())) {
            return createHandlerResult(myCredential, principalFactory.createPrincipal(myCredential.getUsername(), map),
                    warning);
        }
        throw new FailedLoginException("用户名或密码错11误！");
        //判断用户状态 是否锁定 是否过去
    }

    // 判断是否支持自定义用户登入凭证
    @Override
    public boolean supports(Credential credential) {
        // TODO Auto-generated method stub
        return credential instanceof UsernamePasswordCaptchaCredential;
    }

}