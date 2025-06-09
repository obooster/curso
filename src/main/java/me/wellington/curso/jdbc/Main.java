package me.wellington.curso.jdbc;

import me.wellington.curso.jdbc.objects.Profile;
import me.wellington.curso.jdbc.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.logging.Level;

public final class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private final ProfileService profileService = new ProfileService();

    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "warn");
        System.setProperty("org.slf4j.simpleLogger.log.com.zaxxer.hikari", "off");
    }

    public void init() {
        profileService.setupProfiles();

        var scanner = new Scanner(System.in);
        System.out.print("Bom dia! Escreva seu nome a seguir: ");
        var name = scanner.nextLine();
        System.out.print("Certo! Agora a sua idade: ");
        var age = scanner.nextInt();

        profileService.register(new Profile(name, age));
        System.out.println("Registrado com sucesso! Segue pessoas registradas a seguir:\n");

        profileService.getRepository().stream().map(profile -> profile.getName() +  " | " + profile.getAge() + " anos").forEach(System.out::println);
    }

    public static void main(String[] args) {
        new Main().init();
    }

}
