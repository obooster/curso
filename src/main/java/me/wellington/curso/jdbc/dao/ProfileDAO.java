package me.wellington.curso.jdbc.dao;

import me.wellington.curso.jdbc.enums.Field;
import me.wellington.curso.jdbc.objects.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public final class ProfileDAO {
    private final Connection connection;

    public ProfileDAO(Connection connection) {
        this.connection = connection;
    }

    public void init() {
        try {
            var statement = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS conta (
                        numero INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255),
                        idade INT
                    )
                    """);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Profile> getProfiles() {
        var statement = (PreparedStatement) null;
        var profiles = new HashSet<Profile>();

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM conta");

            var result = statement.executeQuery();

            while (result.next()) {
                profiles.add(new Profile(result.getInt(1), result.getString(2), result.getInt(3)));
            }

            statement.close();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return profiles;
    }

    public Profile create(Profile profile) {
        var statement = (PreparedStatement) null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO conta (name, idade) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, profile.getName());
            statement.setInt(2, profile.getAge());

            statement.execute();
            var resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) profile.setNumber(resultSet.getInt(1));
            statement.close();

            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return profile;
    }

    public void delete(int number) {
        var statement = (PreparedStatement) null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM conta WHERE numero = ?");

            statement.setInt(1, number);

            statement.execute();
            statement.close();

            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void change(int number, Field info, Object value) {
        var statement = (PreparedStatement) null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("UPDATE conta SET " + info.object + " = ? WHERE numero = ?");

            statement.setObject(1, value);
            statement.setInt(2, number);

            statement.execute();
            statement.close();

            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
