package me.wellington.curso.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;

public class JPAUtil {
    private static final HashMap<String, Object> FACTORY_CONFIGURATION = new HashMap<>();

    static {
        FACTORY_CONFIGURATION.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        FACTORY_CONFIGURATION.put("javax.persistence.jdbc.url", "jdbc:h2:mem:curso");
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
