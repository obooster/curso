package me.wellington.curso.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionFactory {
    private static final HikariDataSource hikariDataSource;

    static {
        var config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:file:./db/jdbc/curso");
        config.setUsername("sa");
        config.setPassword("");
        config.setDriverClassName("org.h2.Driver");

        config.setMaximumPoolSize(10);

        hikariDataSource = new HikariDataSource(config);
    }

    public Connection getConnection() {
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
