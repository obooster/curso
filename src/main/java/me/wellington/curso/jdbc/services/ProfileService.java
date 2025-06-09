package me.wellington.curso.jdbc.services;

import me.wellington.curso.jdbc.ConnectionFactory;
import me.wellington.curso.jdbc.dao.ProfileDAO;
import me.wellington.curso.jdbc.enums.Field;
import me.wellington.curso.jdbc.objects.Profile;
import me.wellington.curso.jdbc.repositories.ProfileRepository;

import java.util.Comparator;

public final class ProfileService {
    private final ProfileRepository repository = new ProfileRepository();
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public ProfileService() {
        new ProfileDAO(connectionFactory).init();
    }

    public void delete(int number) {
        new ProfileDAO(connectionFactory).delete(number);
    }

    public void register(Profile profile) {
        repository.add(new ProfileDAO(connectionFactory).create(profile));
    }

    public void clear() {
        new ProfileDAO(connectionFactory).clear();
        repository.clear();
    }

    public void change(int number, Field object, Object value) {
        new ProfileDAO(connectionFactory).change(number, object, value);
    }

    public ProfileService setupProfiles() {
        var result = new ProfileDAO(connectionFactory).getProfiles();

        repository.addAll(result);
        repository.sort(Comparator.comparing(Profile::getNumber));

        return this;
    }

    public ProfileRepository getRepository() {
        return repository;
    }
}
