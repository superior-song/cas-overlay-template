package com.suncreate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 *  接口文档配置
 *
 *  @author jie liu
 *  @date 2018/7/8
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public static PropertySourcesPlaceholderConfigurer swaggerProperties() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        return properties;
    }
    private ApiInfo ddtApiInfo() {
        return new ApiInfoBuilder()
                .title("Spring BOOT 单点登录业务接口")
                .version("1.0")
                .build();
    }
    @Bean
    public Docket wfmApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Spring BOOT 单点登录业务接口")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .securitySchemes(apiKeys())
                .securityContexts(securityContext())
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(regex("/v1/.*"))
                .build()
                .apiInfo(ddtApiInfo());
    }
    private ApiInfo sysApiInfo() {
        return new ApiInfoBuilder()
                .title("服务注册接口")
                .version("1.0")
                .build();
    }
    @Bean
    public Docket sysApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("服务管理")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .securitySchemes(apiKeys())
                .securityContexts(securityContext())
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(regex("/services/.*"))
                .build()
                .apiInfo(sysApiInfo());
    }
    private ApiInfo apiApiInfo() {
        return new ApiInfoBuilder()
                .title("Spring BOOT demo2接口")
                .version("1.0")
                .build();
    }
    @Bean
    public Docket apiApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Spring 服务注册接口")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .securitySchemes(apiKeys())
                .securityContexts(securityContext())
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(regex("/services/.*"))
                .build()
                .apiInfo(apiApiInfo());
    }
    @Bean
    UiConfiguration uiConfig() {

        String[] supportedSubmitMethod = UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS;

        return new UiConfiguration(
                null,
                "list",
                "alpha",
                "schema",
                supportedSubmitMethod,
                false,
                true
        );
    }

    private List<ApiKey> apiKeys() {
        List<ApiKey> apiKeys = new ArrayList<ApiKey>();
        // api key的名称，key字段以及传输方式(header or query)
        ApiKey apiKey = new ApiKey("mykey", "api_key", "header");
        apiKeys.add(apiKey);
        return apiKeys;
    }

    private List<SecurityContext> securityContext() {
        List<SecurityContext> securityContexts = new ArrayList<SecurityContext>();
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/*"))
                .build();
        securityContexts.add(securityContext);
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "全局授权");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        List<SecurityReference> securityReferences = new ArrayList<SecurityReference>();
        securityReferences.add(new SecurityReference("mykey", authorizationScopes));

        return securityReferences;
    }

}