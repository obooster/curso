package me.wellington.curso.jdbc.dao;

import me.wellington.curso.jdbc.objects.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Set;

public final class ProfileDAO {
    private final Connection connection;

    public ProfileDAO(Connection connection) {
        this.connection = connection;
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
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return profiles;
    }

    public void create(Profile profile) {
        var statement = (PreparedStatement) null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO conta (numero, nome, idade) VALUES (?, ?, ?)");

            statement.setInt(1, profile.number());
            statement.setString(2, profile.name());
            statement.setInt(3, profile.age());

            statement.execute();
            statement.close();

            connection.commit();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void change(int number, String info, Object value) {
        var statement = (PreparedStatement) null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("UPDATE conta SET " + info + " = ? WHERE numero = ?");

            statement.setObject(1, value);
            statement.setInt(2, number);

            statement.execute();
            statement.close();

            connection.commit();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
