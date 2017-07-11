package com.aiesst.config.database.sql;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManagerFactory;

/**
 * Created by ychost on 17-6-28.
 */
@Configuration
@ComponentScan("com.aiesst.config.database.sql")
public class JinqForJpaConfig {
    @Bean
    @Primary
    @ConditionalOnExpression("'${server.db.sql}' == 'mysql'")
    public JinqJPAStreamProvider mysqlStreamProvider(@Qualifier("mySqlEntityManagerFactory") EntityManagerFactory emf) {
        return new JinqJPAStreamProvider(emf);
    }


    @Bean
    @ConditionalOnExpression("'${server.db.sql}' == 'postgres'")
    public JinqJPAStreamProvider postgresStreamProvider(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory emf) {
        return new JinqJPAStreamProvider(emf);
    }
    @Bean
    @ConditionalOnExpression("'${server.db.sql}' == 'h2'")
    public JinqJPAStreamProvider h2StreamProvider(@Qualifier("h2EntityManagerFactory") EntityManagerFactory emf) {
        return new JinqJPAStreamProvider(emf);
    }
}
