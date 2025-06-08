package me.wellington.curso.jpa.dao;

import me.wellington.curso.jpa.entities.Person;
import me.wellington.curso.jpa.enums.Gender;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public final class PersonDAO {
    private final EntityManager entityManager;

    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(Person person) {
        entityManager.persist(person);
    }

    public void registerAll(Person... persons) {
        Arrays.stream(persons).forEach(this::register);
    }

    public Person find(long id) {
        return entityManager.find(Person.class, id);
    }

    public List<Person> findAll() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    public List<Person> findByName(String name) {
        return entityManager.createQuery("SELECT p FROM Person p WHERE p.name = ?1", Person.class)
                .setParameter(1, name)
                .getResultList();
    }

    public Gender findGenderByName(String name) {
        return entityManager.createQuery("SELECT p.gender FROM Person p WHERE p.name = ?1",
                        Gender.class)
                .setParameter(1, name)
                .getSingleResult();
    }

    public List<Person> findByGender(String gender) {
        return entityManager.createQuery("SELECT p FROM Person p WHERE p.gender.name = ?1", Person.class)
                .setParameter(1, gender)
                .getResultList();
    }

}
