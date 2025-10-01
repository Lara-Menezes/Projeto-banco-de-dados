package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;


/**
 * 
 * 
 */
public class GenericDAO<T> {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TarefasPU");

    private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void salvar(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public T buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    public List<T> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            T obj = em.find(entityClass, id);
            if (obj != null) {
                em.getTransaction().begin();
                em.remove(obj);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
