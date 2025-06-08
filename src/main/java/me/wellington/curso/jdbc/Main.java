package me.wellington.curso.jdbc;

import me.wellington.curso.jdbc.objects.Profile;
import me.wellington.curso.jdbc.services.ProfileService;

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
