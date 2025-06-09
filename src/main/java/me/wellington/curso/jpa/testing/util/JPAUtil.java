package me.wellington.curso.jpa.testing.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class JPAUtil {
    private static final HashMap<String, Object> FACTORY_CONFIGURATION = new HashMap<>();

    static {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        FACTORY_CONFIGURATION.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        FACTORY_CONFIGURATION.put("javax.persistence.jdbc.url", "jdbc:h2:file:./db/curso");
        FACTORY_CONFIGURATION.put("javax.persistence.jdbc.user", "sa");
        FACTORY_CONFIGURATION.put("javax.persistence.jdbc.password", "");
        FACTORY_CONFIGURATION.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    }

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("curso", FACTORY_CONFIGURATION);

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

}
