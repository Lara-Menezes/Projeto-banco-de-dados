package dao;

import jakarta.persistence.*;
import java.util.List;

public class GenericDAO<T> {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TarefasPU");

    private final Class<T> entityClass;
    EntityManager em = emf.createEntityManager();

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void salvar(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public T buscarPorId(Long id) {

        T obj = em.find(entityClass, id);
        em.close();
        return obj;
    }

    public List<T> listarTodos() {

        List<T> lista = em.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
        em.close();
        return lista;
    }

    public void atualizar(T entity) {

        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir(Long id) {

        T obj = em.find(entityClass, id);
        if (obj != null) {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
        }
        em.close();
    }
}
