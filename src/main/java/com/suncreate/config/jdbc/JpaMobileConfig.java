package com.suncreate.config.jdbc;

import com.suncreate.entity.User;
import com.suncreate.repository.JpaMobileUserRepository;
import com.suncreate.repository.UserRepository;
import lombok.val;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.configuration.model.support.jpa.JpaConfigDataHolder;
import org.apereo.cas.configuration.support.JpaBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apereo.cas.util.CollectionUtils;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

@Configuration("jpaMobileConfig")
@EnableConfigurationProperties(value = {CasConfigurationProperties.class,MobileJpaProperties.class})
@EnableTransactionManagement(proxyTargetClass = true)
public class JpaMobileConfig  {
    @Autowired
    private CasConfigurationProperties casProperties;

    @RefreshScope
    @Bean
    public HibernateJpaVendorAdapter mobileUserVendorAdapter() {
        return JpaBeans.newHibernateJpaVendorAdapter(casProperties.getJdbc());
    }
    @Bean
    public MobileJpaProperties mobileJpaProperties(){
        return new MobileJpaProperties();
    }
    @Bean
    public DataSource dataSource()  {
        MobileJpaProperties mobileJpaProperties = mobileJpaProperties();
        return JpaBeans.newDataSource(mobileJpaProperties);
    }

    public List<String> jpaMobilePackagesToScan() {
        return CollectionUtils.wrap(User.class.getPackage().getName());
    }
    @Lazy
    @Bean
    public LocalContainerEntityManagerFactoryBean mobileUserEntityManagerFactory()  {

        return JpaBeans.newHibernateEntityManagerFactoryBean(
                new JpaConfigDataHolder(
                        mobileUserVendorAdapter(),
                        "jpaMobileContext",
                        jpaMobilePackagesToScan(),
                        dataSource()),
                mobileJpaProperties());
    }

    @Autowired
    @Bean
    public PlatformTransactionManager transactionManagerMobileId(@Qualifier("mobileUserEntityManagerFactory") final EntityManagerFactory emf) {
        val mgmr = new JpaTransactionManager();
        mgmr.setEntityManagerFactory(emf);
        return mgmr;
    }

    @Bean
    public UserRepository mobileUserRepository() {
        return new JpaMobileUserRepository();
    }

}