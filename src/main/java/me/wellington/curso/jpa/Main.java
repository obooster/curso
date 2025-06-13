package me.wellington.curso.jpa;

import me.wellington.curso.jpa.dao.MotherDAO;
import me.wellington.curso.jpa.dao.PersonDAO;
import me.wellington.curso.jpa.entities.Mother;
import me.wellington.curso.jpa.entities.Person;
import me.wellington.curso.jpa.entities.Gender;
import me.wellington.curso.jpa.util.JPAUtil;

public class Main {

    public void init() {
        setupPersons();
        var entityManager = JPAUtil.getEntityManager();
        var personDAO = new PersonDAO(entityManager);
        var motherDAO = new MotherDAO(entityManager);

       // personDAO.findAll().forEach(System.out::println);
        System.out.println(personDAO.findByName("Wellington"));
       // motherDAO.findAll().forEach(System.out::println);
    }

    public void setupPersons() {
        var maleGender = new Gender("MALE");
        var femaleGender = new Gender("FEMALE");

        var mother = new Mother("Gi");
        var person = new Person("Wellington", 16, maleGender);
        var person2 = new Person("Ingrid", 16, femaleGender);
        var person3 = new Person("Chrystina", 18, femaleGender);

        mother.setPersons(person, person2, person3);

        var entityManager = JPAUtil.getEntityManager();
        var personDAO = new PersonDAO(entityManager);
        var motherDAO = new MotherDAO(entityManager);

        entityManager.getTransaction().begin();

        motherDAO.register(mother);
        personDAO.registerAll(person, person2, person3);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void main(String[] args) {
        new Main().init();
    }

}
