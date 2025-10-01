package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TarefasPU"); // nome do persistence-unit no persistence.xml

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
