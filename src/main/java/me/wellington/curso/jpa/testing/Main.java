package me.wellington.curso.jpa.testing;

import me.wellington.curso.jpa.testing.dao.HumanDAO;
import me.wellington.curso.jpa.testing.entities.Human;
import me.wellington.curso.jpa.testing.enums.Gender;
import me.wellington.curso.jpa.testing.util.JPAUtil;

import java.util.Scanner;

public final class Main {

    public void init() {
        var scanner = new Scanner(System.in);

        System.out.println("""
                
                Bem vindo ao aplicativo de cadastro humano!
                Para começar, digite o seu nome completo abaixo:""");
        var name = scanner.nextLine().toUpperCase();
        System.out.print("Agora, a sua idade: ");
        var age = scanner.nextInt();
        System.out.println("Agora, o seu gênero (MALE/FEMALE): ");
        var gender = scanner.next().toUpperCase();

        var entityManager = JPAUtil.getEntityManager();
        var human = new Human(name, age, Gender.valueOf(gender));

        entityManager.getTransaction().begin();

        entityManager.persist(human);

        entityManager.getTransaction().commit();

        System.out.println("\nHumano registrado com sucesso! Segue abaixo lista de humanos registrados.");
        new HumanDAO(entityManager).getAllHumansRegistered().forEach(System.out::println);
    }

    public static void main(String[] args) {
        JPAUtil.getEntityManager();
        while (true) {
            new Main().init();
        }
    }

}
