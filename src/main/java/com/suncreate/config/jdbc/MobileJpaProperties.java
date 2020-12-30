package com.suncreate.config.jdbc;


import org.apereo.cas.configuration.model.support.jpa.AbstractJpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ConfigurationProperties(prefix = "mobile.jpa" ,ignoreUnknownFields = false)
@PropertySource("classpath:application-jdbc.properties")
public class MobileJpaProperties extends AbstractJpaProperties {

}
