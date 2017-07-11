package com.aiesst.config.database.sql;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by ychost on 17-6-28.
 */
@Configuration
@ComponentScan("com.aiesst.config.database.sql")
public class JpaSqlCommonConfig {

    private final MySqlConnectionConfig mySqlConnectionConfig;
    private final PostgresConnectionConfig postgresConnectionConfig;
    @Value("${server.db.path}")
    private String path;

    JpaSqlCommonConfig(MySqlConnectionConfig mySqlConnectionConfig, PostgresConnectionConfig postgresConnectionConfig) {
        this.mySqlConnectionConfig = mySqlConnectionConfig;
        this.postgresConnectionConfig = postgresConnectionConfig;
    }


    /**
     * mysql数据源，使用c3p0连接池
     *
     * @return
     */
    DataSource mySqlC3P0DataSource() throws Exception {
        // 生成一个mysql的数据源，配置其host,端口用户名及密码
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        this.setC3P0DataSource(dataSource, this.mySqlConnectionConfig);
        return dataSource;
    }

    /**
     * postgresql数据源
     *
     * @return
     * @throws Exception
     */
    DataSource postgresC3P0DataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        this.setC3P0DataSource(dataSource, this.postgresConnectionConfig);
        return dataSource;
    }

    void setC3P0DataSource(ComboPooledDataSource dataSource, ISqlConnectionConfig sqlConnectionConfig) throws Exception {
        dataSource.setDriverClass(sqlConnectionConfig.getDriverClass());
        dataSource.setJdbcUrl(sqlConnectionConfig.getJdbcUrl());
        dataSource.setUser(sqlConnectionConfig.getUsername());
        dataSource.setPassword(sqlConnectionConfig.getPassword());
        dataSource.setMaxPoolSize(15);     // 最大链接数
        dataSource.setMaxIdleTime(60);
        dataSource.setNumHelperThreads(3);
        dataSource.setAcquireIncrement(2); // 每次增加链接数
        //// TODO: 17-6-28  postgres会抛出异常
//        dataSource.setTestConnectionOnCheckin(true);
//        dataSource.setTestConnectionOnCheckout(true);
    }

    DataSource h2DataSource() {
        EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
        edb.setType(EmbeddedDatabaseType.H2);
        return edb.build();
    }

    String getMysqlScanPath() {
        return path;
    }

    String getPostgresScanPath() {
        return path;
    }
}
