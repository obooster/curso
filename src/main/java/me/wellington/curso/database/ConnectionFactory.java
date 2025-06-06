package me.wellington.curso.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {
    private static final String SQL_URL = "";

    private HikariDataSource createDataSource() {
        var config = new HikariConfig();
        config.setJdbcUrl(SQL_URL);
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

    public Connection getConnection() {
        try {
            return createDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
