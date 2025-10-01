package dao;

import jakarta.persistence.EntityManager;
import model.Tarefa;

import java.util.List;

public class TarefaDAO extends GenericDAO<Tarefa> {
    public TarefaDAO() {
        super(Tarefa.class);
    }

    public List<Tarefa> listarPorUsuario(Long usuarioId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tarefa t WHERE t.owner.id = :usuarioId", Tarefa.class)
                     .setParameter("usuarioId", usuarioId)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Tarefa> listarPorCategoria(Long categoriaId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tarefa t WHERE t.categoria.id = :categoriaId", Tarefa.class)
                     .setParameter("categoriaId", categoriaId)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
