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
        new ProfileDAO(connectionFactory.getConnection()).init();
    }

    public void delete(int number) {
        new ProfileDAO(connectionFactory.getConnection()).delete(number);
    }

    public void register(Profile profile) {
        repository.add(new ProfileDAO(connectionFactory.getConnection()).create(profile));
    }

    public void change(int number, Field object, Object value) {
        new ProfileDAO(connectionFactory.getConnection()).change(number, object, value);
    }

    public void setupProfiles() {
        var result = new ProfileDAO(connectionFactory.getConnection()).getProfiles();

        repository.addAll(result);
        repository.sort(Comparator.comparing(Profile::getNumber));
    }

    public ProfileRepository getRepository() {
        return repository;
    }
}
