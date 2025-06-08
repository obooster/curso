package me.wellington.curso.jdbc.services;

import me.wellington.curso.jdbc.ConnectionFactory;
import me.wellington.curso.jdbc.dao.ProfileDAO;
import me.wellington.curso.jdbc.objects.Profile;
import me.wellington.curso.jdbc.repositories.ProfileRepository;

public final class ProfileService {
    private final ProfileDAO profileDAO;
    private final ProfileRepository repository = new ProfileRepository();
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public ProfileService() {
        profileDAO = new ProfileDAO(connectionFactory.getConnection());
    }

    public void delete(int number) {
        new ProfileDAO(connectionFactory.getConnection()).delete(number);
    }

    public void register(Profile profile) {
        repository.add(profile);
        profileDAO.create(profile);
    }

    public void change(int number, String object, Object value) {
        profileDAO.change(number, object, value);
    }

    public void setupProfiles() {
        var result = profileDAO.getProfiles();

        repository.addAll(result);
    }

    public ProfileRepository getRepository() {
        return repository;
    }
}
