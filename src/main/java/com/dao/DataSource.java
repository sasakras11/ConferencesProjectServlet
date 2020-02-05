package com.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {


    private  HikariDataSource ds;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSource.class);


    public DataSource(String propFile){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(propFile));
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername( properties.getProperty("db.username"));
            config.setPassword( properties.getProperty("db.password"));
            config.setDriverClassName(properties.getProperty("db.driver"));
            config.addDataSourceProperty( "cachePrepStmts" , "true" );
            config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
            config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
            config.setMaximumPoolSize(100);
            ds = new HikariDataSource(config);

        } catch (IOException e) {
            LOGGER.warn(String.format("properties file url for connection pool is wrong : %s . Exception : %s",propFile,e));
        }

    }



    public  Connection getConnection() throws SQLException {

        return ds.getConnection();
    }

}