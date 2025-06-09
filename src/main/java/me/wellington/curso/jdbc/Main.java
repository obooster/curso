package me.wellington.curso.jdbc;

import me.wellington.curso.jdbc.objects.Profile;
import me.wellington.curso.jdbc.services.ProfileService;

import java.util.Scanner;

public final class Main {
    private final ProfileService profileService = new ProfileService();

    public void init() {
        profileService.setupProfiles();

        var scanner = new Scanner(System.in);
        System.out.println("Bom dia! Escreva seu nome a seguir: ");
        var name = scanner.nextLine();
        System.out.println("Certo! Agora a sua idade: ");
        var age = scanner.nextInt();

        profileService.register(new Profile(name, age));
        System.out.println("Registrado com sucesso! Segue pessoas registradas a seguir:\n\n");
        profileService.getRepository().forEach(System.out::println);
    }

    public static void main(String[] args) {
        new Main().init();
    }

}
