package me.wellington.curso.jpa.dao;

import me.wellington.curso.jpa.entities.Person;
import me.wellington.curso.jpa.entities.Gender;

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

    public Person findByName(String name) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(Person.class);
        var from = query.from(Person.class);

        var filter = builder.and(builder.equal(from.get("name"), name));

        query.where(filter);

        return entityManager.createQuery(query).getSingleResult();
       // return entityManager.createQuery("SELECT p FROM Person p WHERE p.name = ?1", Person.class)
        //        .setParameter(1, name)
        //        .getResultList();
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
