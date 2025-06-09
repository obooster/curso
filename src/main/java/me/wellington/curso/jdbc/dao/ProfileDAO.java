package me.wellington.curso.jdbc.dao;

import me.wellington.curso.jdbc.ConnectionFactory;
import me.wellington.curso.jdbc.enums.Field;
import me.wellington.curso.jdbc.objects.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public final class ProfileDAO {
    private final ConnectionFactory connectionFactory;

    public ProfileDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void init() {
        try (var connection = connectionFactory.getConnection()) {
            connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS conta (
                        numero INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255),
                        idade INT
                    )
                    """).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Profile> getProfiles() {
        var profiles = new HashSet<Profile>();

        try (var connection = connectionFactory.getConnection() ;
            var statement = connection.prepareStatement("SELECT * FROM conta");
            var result = statement.executeQuery()) {

            while (result.next()) {
                profiles.add(new Profile(result.getInt(1), result.getString(2), result.getInt(3)));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return profiles;
    }

    public Profile create(Profile profile) {

        try (var connection = connectionFactory.getConnection();
             var statement = connection.prepareStatement("INSERT INTO conta (name, idade) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);

            statement.setString(1, profile.getName());
            statement.setInt(2, profile.getAge());

            statement.executeUpdate();

            try (var resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) profile.setNumber(resultSet.getInt(1));
            }

            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return profile;
    }

    public void clear() {
        try (var connection = connectionFactory.getConnection();
            var statement = connection.prepareStatement("TRUNCATE TABLE conta RESTART IDENTITY")) {
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int number) {

        try (var connection = connectionFactory.getConnection();
             var statement = connection.prepareStatement("DELETE FROM conta WHERE numero = ?")) {
            connection.setAutoCommit(false);

            statement.setInt(1, number);

            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void change(int number, Field info, Object value) {

        try (var connection = connectionFactory.getConnection();
             var statement = connection.prepareStatement("UPDATE conta SET " + info.object + " = ? WHERE numero = ?")) {
            connection.setAutoCommit(false);

            statement.setObject(1, value);
            statement.setInt(2, number);

            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
