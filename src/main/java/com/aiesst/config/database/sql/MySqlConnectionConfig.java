package com.aiesst.config.database.sql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ychost on 17-6-28.
 */
@Configuration
public class MySqlConnectionConfig implements ISqlConnectionConfig {
    @Value("${server.mysql.username}")
    private String username;
    @Value("${server.mysql.password}")
    private String password;
    @Value("${server.mysql.driver.class}")
    private String driverClass;
    @Value("${server.mysql.jdbc.url}")
    private String jdbcUrl;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getDriverClass() {
        return driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

}
