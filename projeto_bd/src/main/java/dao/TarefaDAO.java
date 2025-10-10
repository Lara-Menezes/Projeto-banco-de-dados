package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Tarefa;
import java.util.List;

/*
 * Classe responsável por extender os comportamentos genéricos do dao genérico para persistir as tarefas
 */

public class TarefaDAO extends GenericDAO<Tarefa> {

    public TarefaDAO() {
        super(Tarefa.class);
    }

    public List<Tarefa> listarPorUsuario(Long usuarioId) {
        EntityManager em = null;
        try {
            em = super.emf.createEntityManager();
            TypedQuery<Tarefa> query = em.createQuery(
                    "SELECT t FROM Tarefa t WHERE t.owner.id = :usuarioId", Tarefa.class);
            query.setParameter("usuarioId", usuarioId);
            return query.getResultList();
        } finally {
            if (em != null) em.close();
        }
    }

    public List<Tarefa> listarPorCategoria(Long categoriaId) {
        EntityManager em = null;
        try {
            em = super.emf.createEntityManager();
            TypedQuery<Tarefa> query = em.createQuery(
                    "SELECT t FROM Tarefa t WHERE t.categoria.id = :categoriaId", Tarefa.class);
            query.setParameter("categoriaId", categoriaId);
            return query.getResultList();
        } finally {
            if (em != null) em.close();
        }
    }

    public List<Tarefa> listarPorUsuarioECategoria(Long usuarioId, Long categoriaId) {
        EntityManager em = null;
        try {
            em = super.emf.createEntityManager();
            TypedQuery<Tarefa> query = em.createQuery(
                    "SELECT t FROM Tarefa t WHERE t.owner.id = :usuarioId AND t.categoria.id = :categoriaId", Tarefa.class);
            query.setParameter("usuarioId", usuarioId);
            query.setParameter("categoriaId", categoriaId);
            return query.getResultList();
        } finally {
            if (em != null) em.close();
        }
    }
}
