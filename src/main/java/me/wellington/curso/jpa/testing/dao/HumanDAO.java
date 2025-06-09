package me.wellington.curso.jpa.testing.dao;

import me.wellington.curso.jpa.testing.entities.Human;

import javax.persistence.EntityManager;
import java.util.List;

public final class HumanDAO {
    private final EntityManager entityManager;

    public HumanDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Human> getAllHumansRegistered() {
        return entityManager.createQuery("SELECT h FROM Human h", Human.class)
                .getResultList();
    }

}
