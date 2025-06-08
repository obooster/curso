package me.wellington.curso.jpa;

import me.wellington.curso.jpa.dao.GenderDAO;
import me.wellington.curso.jpa.dao.PersonDAO;
import me.wellington.curso.jpa.entities.Person;
import me.wellington.curso.jpa.enums.Gender;
import me.wellington.curso.jpa.util.JPAUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public void init() {
        setupPersons();
        var entityManager = JPAUtil.getEntityManager();
        var personDAO = new PersonDAO(entityManager);

        var person = personDAO.find(1L);

        System.out.println(person);
        personDAO.findAll().stream().map(Person::getName).forEach(System.out::println);
    }

    public void setupPersons() {
        var maleGender = new Gender("MALE");
        var femaleGender = new Gender("FEMALE");

        var person = new Person("Wellington", 16, maleGender);
        var person2 = new Person("Ingrid", 16, femaleGender);
        var person3 = new Person("Chrystina", 18, femaleGender);

        var entityManager = JPAUtil.getEntityManager();
        var personDAO = new PersonDAO(entityManager);
        var genderDAO = new GenderDAO(entityManager);

        entityManager.getTransaction().begin();

        genderDAO.registerAll(maleGender, femaleGender);
        personDAO.registerAll(person, person2, person3);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        new Main().init();
    }

}
