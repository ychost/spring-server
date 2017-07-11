package com.aiesst.config.database.sql;

/**
 * Created by ychost on 17-6-28.
 */
public interface ISqlConnectionConfig {
    String getPassword();

    String getDriverClass();

    String getUsername();

    String getJdbcUrl();
}
