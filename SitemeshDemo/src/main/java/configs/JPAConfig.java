package configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
