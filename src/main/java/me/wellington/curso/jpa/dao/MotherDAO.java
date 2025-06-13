package me.wellington.curso.jpa.dao;

import me.wellington.curso.jpa.entities.Gender;
import me.wellington.curso.jpa.entities.Mother;
import me.wellington.curso.jpa.entities.Person;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public final class MotherDAO {
    private final EntityManager entityManager;

    public MotherDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(Mother mother) {
        entityManager.persist(mother);
    }

    public void registerAll(Mother... persons) {
        Arrays.stream(persons).forEach(this::register);
    }

    public Mother find(long id) {
        return entityManager.find(Mother.class, id);
    }

    public List<Mother> findAll() {
        return entityManager.createQuery("SELECT p FROM Mother p", Mother.class).getResultList();
    }

    public List<Mother> findByName(String name) {
        return entityManager.createQuery("SELECT p FROM Mother p WHERE p.name = ?1", Mother.class)
                .setParameter(1, name)
                .getResultList();
    }

    public List<Person> findByMother(Mother mother) {
        return entityManager.createQuery("SELECT p FROM Person p JOIN FETCH p.mother WHERE p.mother = ?1", Person.class)
                .setParameter(1, mother)
                .getResultList();
    }

}
