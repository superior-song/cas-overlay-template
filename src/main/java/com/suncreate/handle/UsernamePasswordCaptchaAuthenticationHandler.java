package com.suncreate.handle;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;

import com.cas.login.CaptchaException;
import com.cas.login.PasswordEncryption;
import com.cas.login.UsernamePasswordCaptchaCredential;
import org.apache.commons.lang.StringUtils;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.MessageDescriptor;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 自定义用户认证核心代码
 * @author zzg
 *
 */
public class UsernamePasswordCaptchaAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {

    public UsernamePasswordCaptchaAuthenticationHandler(String name, ServicesManager servicesManager,
                                                        PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential)
            throws GeneralSecurityException, PreventedException {
        // TODO Auto-generated method stub
        // 用户凭证
        UsernamePasswordCaptchaCredential myCredential = (UsernamePasswordCaptchaCredential) credential;
       /* String requestCaptcha = myCredential.getCaptcha();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object attribute = attributes.getRequest().getSession().getAttribute("captcha");

        String realCaptcha = attribute == null ? null : attribute.toString();

        if (StringUtils.isBlank(requestCaptcha) || !requestCaptcha.equalsIgnoreCase(realCaptcha)) {
            throw new CaptchaException("验证码错误");
        }*/

        // 验证用户名和密码
        DriverManagerDataSource d = new DriverManagerDataSource();
        d.setDriverClassName("com.mysql.jdbc.Driver");
        d.setUrl(
                "jdbc:mysql://192.168.1.73:3306/boot-security?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
        d.setUsername("root");
        d.setPassword("digipower");

        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(d);

        // 查询数据库加密的的密码
        Map<String, Object> user = template.queryForMap("select pswd from u_user where nickname = ?",
                myCredential.getUsername());

        if (user == null) {
            throw new AccountNotFoundException("用户名输入错误或用户名不存在");
        }

        // 返回多属性
        Map<String, Object> map = new HashMap<>();
        map.put("username", myCredential.getUsername());

        // 密码加密验证(MD5 32位 大写)
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        List<MessageDescriptor>  warning = new ArrayList<MessageDescriptor>();
        if (passwordEncryption.matches(myCredential.getPassword(), user.get("pswd").toString())) {
            return createHandlerResult(myCredential, principalFactory.createPrincipal(myCredential.getUsername(), map),
                    warning);
        }

        throw new FailedLoginException("密码输入错误");
    }

    // 判断是否支持自定义用户登入凭证
    @Override
    public boolean supports(Credential credential) {
        // TODO Auto-generated method stub
        return credential instanceof UsernamePasswordCaptchaCredential;
    }

}