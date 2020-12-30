package com.suncreate.config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.suncreate.repository")
@ComponentScan(basePackages = {"com.suncreate.sso","com.suncreate.config.mybatis"})
public class SpringConfig {
}