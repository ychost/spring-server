package com.aiesst.config.database.sql;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ychost on 17-6-27.
 */
@Configuration
@EnableTransactionManagement()
@ConditionalOnExpression("'${server.db.sql}' == 'postgres'")
@ComponentScan("com.aiesst.config.database.sql")
class JpaPostgresConfig {

    private final JpaSqlCommonConfig jpaSqlCommConfig;

    JpaPostgresConfig(JpaSqlCommonConfig jpaSqlCommonConfig) {
        this.jpaSqlCommConfig = jpaSqlCommonConfig;
    }

    //
    @Bean(name = "postgresEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() throws Exception {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        // 设置数据源
        entityManagerFactoryBean.setDataSource(jpaSqlCommConfig.postgresC3P0DataSource());
        entityManagerFactoryBean.setPackagesToScan(jpaSqlCommConfig.getPostgresScanPath());
        // Hibernate适配器
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        entityManagerFactoryBean.setJpaProperties(properties);

        // 设置适配器
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        //// TODO: 17-6-28   postgres 会抛出异常但是不影响使用？
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
