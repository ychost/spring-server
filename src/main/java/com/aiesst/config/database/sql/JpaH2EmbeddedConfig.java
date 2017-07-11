package com.aiesst.config.database.sql;

/**
 * Created by ychost on 17-6-28.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * 调试时，可选用h2数据库
 */
@Configuration
@ConditionalOnExpression("'${server.db.sql}' == 'h2'")
@ComponentScan("com.aiesst.config.database.sql")
public class JpaH2EmbeddedConfig {

    private final JpaSqlCommonConfig jpaSqlCommConfig;

    @Autowired
    protected JpaH2EmbeddedConfig(JpaSqlCommonConfig jpaSqlCommConfig) {
        this.jpaSqlCommConfig = jpaSqlCommConfig;
    }

    @Bean(name = "h2EntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        // 设置数据源
        entityManagerFactoryBean.setDataSource(jpaSqlCommConfig.h2DataSource());
        entityManagerFactoryBean.setPackagesToScan(jpaSqlCommConfig.getMysqlScanPath());

        // Hibernate适配器
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        entityManagerFactoryBean.setJpaProperties(properties);

        // 设置适配器
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
