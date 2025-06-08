package me.wellington.curso.database;

import me.wellington.curso.database.objects.Profile;
import me.wellington.curso.database.repositories.ProfileRepository;
import me.wellington.curso.database.services.ProfileService;

import java.sql.SQLException;

public final class Main {
    private final ProfileService profileService = new ProfileService();

    public void init() {
        profileService.setupProfiles();

        profileService.getRepository().stream().map(Profile::name).forEach(System.out::println);

        System.out.println("\nopa");
    }

    public static void main(String[] args) {
        new Main().init();
    }

}
