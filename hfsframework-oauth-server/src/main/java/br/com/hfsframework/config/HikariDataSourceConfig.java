package br.com.hfsframework.config;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceConfig {

    private static HikariConfig config = new HikariConfig("hikari.properties");
    private static HikariDataSource ds;

    static {
        ds = new HikariDataSource(config);
    }

    private HikariDataSourceConfig() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
