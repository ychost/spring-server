package com.aiesst.config.database.sql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ychost on 17-6-28.
 */
@Configuration
public class PostgresConnectionConfig implements ISqlConnectionConfig {
    @Value("${server.pg.username}")
    private String username;
    @Value("${server.pg.password}")
    private String password;
    @Value("${server.pg.driver.class}")
    private String driverClass;
    @Value("${server.pg.jdbc.url}")
    private String jdbcUrl;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClass() {
        return this.driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }
}

