package com.suncreate.config.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * mybatis多数据源配置
 *
 * @author egoo
 * @date 2019/2/18
 */
@Configuration
@Slf4j
public class MyBatisConfig implements ApplicationContextAware {

	private ApplicationContext context;

	/**
	 * primary数据源配置  oracle数据库配置
	 * @return
	 */
	@Primary
	@Bean
	@ConfigurationProperties("spring.primary.datasource.druid")
	public DataSource primaryDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "primaryFactory")
	@Qualifier("primaryFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactoryPrimary() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(primaryDataSource());
		factoryBean.setConfigLocation(context.getResource("classpath:mybatis-config.xml"));
		factoryBean.setMapperLocations(context.getResources("classpath*:mappers/repository/**/*Mapper.xml"));
		return factoryBean.getObject();
	}

	/**
	 * 事物管理
	 *
	 * @param primaryDataSource
	 * @return
	 */
	@Bean
	@Resource
	public PlatformTransactionManager primaryTxManager(DataSource primaryDataSource) {
		return new DataSourceTransactionManager(primaryDataSource);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}