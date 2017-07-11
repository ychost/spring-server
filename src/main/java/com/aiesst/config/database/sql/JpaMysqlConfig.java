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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;


@Configuration
@EnableTransactionManagement()
@ConditionalOnExpression("'${server.db.sql}' == 'mysql'")
@ComponentScan("com.aiesst.config.database.sql")
public class JpaMysqlConfig {
    private final JpaSqlCommonConfig jpaSqlCommConfig;

    @Autowired
    protected JpaMysqlConfig(JpaSqlCommonConfig jpaSqlCommConfig) {
        this.jpaSqlCommConfig = jpaSqlCommConfig;
    }

    @Bean(name = "mySqlEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() throws Exception {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        // 设置数据源
        entityManagerFactoryBean.setDataSource(jpaSqlCommConfig.mySqlC3P0DataSource());
        entityManagerFactoryBean.setPackagesToScan(jpaSqlCommConfig.getMysqlScanPath());
        // Hibernate适配器
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        entityManagerFactoryBean.setJpaProperties(properties);

        // 设置适配器
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("mySqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    //配置方式二
//    @Bean
//    public LocalContainerEntityManagerFactoryBean emBean(EntityManagerFactoryBuilder builder, @Qualifier("mySqlC3P0DataSource") DataSource mySqlC3P0DataSource) {
//        Map<String, String> props = new HashMap<>();
//        // 设置数据库连接以及使用mysql数据库
//        props.put("dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
//        // hibernate启动时，自动创建表
//        props.put("hibernate.hbm2ddl.auto", "update");
//        props.put("hibernate.show_sql", "true");
//
//        return builder.mySqlC3P0DataSource(mySqlC3P0DataSource)
//                .packages("gift.aiesst.request.database.mysql")
//                .properties(props)
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager mainTransactionManager(EntityManagerFactory mainEmf) {
//        return new JpaTransactionManager(mainEmf);
//    }

}
