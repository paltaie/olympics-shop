package com.wsp.olympics.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableJpaRepositories(basePackages = "com.wsp.olympics.repository")
@EnableTransactionManagement
public class OlyJpaConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localEntityManagerFactoryBean.setDataSource(dataSource());
        localEntityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        localEntityManagerFactoryBean.setPackagesToScan("com.wsp.olympics.model");
        localEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return localEntityManagerFactoryBean;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("org.h2.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:h2:~/test;MODE=ORACLE;AUTO_SERVER=true");
        comboPooledDataSource.setUser("sa");
        comboPooledDataSource.setPassword("");
        comboPooledDataSource.setMaxStatements(1000);
        comboPooledDataSource.setTestConnectionOnCheckout(true);
        return comboPooledDataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws PropertyVetoException {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
}
