package me.wellington.curso.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionFactory {

    private HikariDataSource createDataSource() {
        var config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:file:./db/jdbc/curso");
        config.setUsername("sa");
        config.setPassword("");
        config.setDriverClassName("org.h2.Driver");

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
