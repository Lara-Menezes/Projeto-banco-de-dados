package dao;

import jakarta.persistence.*;

public class JPAUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TarefasPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
