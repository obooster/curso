package me.wellington.curso.database.services;

import me.wellington.curso.database.ConnectionFactory;
import me.wellington.curso.database.dao.ProfileDAO;
import me.wellington.curso.database.objects.Profile;
import me.wellington.curso.database.repositories.ProfileRepository;

public final class ProfileService {
    private ProfileRepository repository = new ProfileRepository();
    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public void delete(int number) {
        new ProfileDAO(connectionFactory.getConnection(), this).delete(number);
    }

    public void register(Profile profile) {
        repository.add(profile);
        new ProfileDAO(connectionFactory.getConnection(), this).create(profile);
    }

    public void change(int number, String object, Object value) {
        new ProfileDAO(connectionFactory.getConnection(), this).change(number, object, value);
    }

    public void setupProfiles() {
        var result = new ProfileDAO(connectionFactory.getConnection(), this).getProfiles();

        repository.addAll(result);
    }

    public ProfileRepository getRepository() {
        return repository;
    }
}
