package me.wellington.curso.jpa.dao;

import me.wellington.curso.jpa.enums.Gender;

import javax.persistence.EntityManager;
import java.util.Arrays;

public final class GenderDAO {
    private final EntityManager entityManager;

    public GenderDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(Gender gender) {
        entityManager.persist(gender);
    }

    public void registerAll(Gender... genders) {
        Arrays.stream(genders).forEach(this::register);
    }

}
