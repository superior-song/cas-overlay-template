package com.suncreate.sso;

import com.suncreate.common.ApiResponse;
import com.suncreate.common.SsoRegister;
import org.apache.commons.lang.StringUtils;
import org.apereo.cas.services.RegexRegisteredService;
import org.apereo.cas.services.RegisteredService;
import org.apereo.cas.services.ReturnAllAttributeReleasePolicy;
import org.apereo.cas.services.ServicesManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;

/**
 * @author anumbrella
 */
@RestController
@RequestMapping("/services")
public class ServicesManagerController {

    private Logger logger = LoggerFactory.getLogger(ServicesManagerController.class);
    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;


    /**
     * 添加cas客户端
     * 增加了单点退出功能，cas退出默认使用隐式退出
     * protocol 代表的是协议，比如: http或者https的协议
     */
    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public ApiResponse addService(@ApiParam("json参数") @RequestBody @Validated SsoRegister ssoRegister) throws IOException {
        ApiResponse result = new ApiResponse();
        String url = ssoRegister.getServiceId();
        RegisteredService svc = servicesManager.findServiceBy(url);
        if (svc != null) {
            result.setCode(204);
            result.setMessage("该服务已经注册，清勿重新注册！");
            return result;
       }
       try {
           //serviceId,可以配置为正则匹配
           //String serviceId = "^"+url;
           RegexRegisteredService service = new RegexRegisteredService();
           ReturnAllAttributeReleasePolicy re = new ReturnAllAttributeReleasePolicy();
           service.setServiceId(url);
           service.setId(ssoRegister.getId());
           service.setAttributeReleasePolicy(re);
           //将name统一设置为servicesId
           service.setName(ssoRegister.getName());
           service.setDescription(ssoRegister.getDespriction());//描述
           //单点登出
           if(StringUtils.isNotEmpty(ssoRegister.getUrl())){
               service.setLogoutUrl(new URL(ssoRegister.getUrl()));
           }
           servicesManager.save(service, true);
           servicesManager.load();
       }catch (Exception e){
           result.init50XResponse();
           result.setMessage(e.getMessage());
           logger.error("服务器错误" + e );
       }
        return result;
    }

    /**
     * 删除服务
     *
     * @param serviceId
     * @return
     */
    @RequestMapping(value = "/deleteService", method = RequestMethod.GET)
    public String delService(@ApiParam(name = "serviceId",value="服务id") @RequestParam(value = "serviceId", required=true) String serviceId) {
        String res = "";
        RegisteredService svc = servicesManager.findServiceBy(serviceId);
        if (svc != null) {
            try {
                servicesManager.delete(svc);
            } catch (Exception e) {
                if (null == servicesManager.findServiceBy(serviceId)) {
                    res = "success";
                    servicesManager.load();
                } else {
                    res = "failed";
                }
            }
        }
        return res;
    }
}