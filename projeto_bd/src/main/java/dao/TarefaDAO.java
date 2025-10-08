package dao;

import jakarta.persistence.EntityManager;
import model.Tarefa;
import java.util.List;

/*
 * Classe responsável por extender os comportamentos genéricos do dao genérico para persistir as tarefas
 */
public class TarefaDAO extends GenericDAO<Tarefa> {

    public TarefaDAO() {
        super(Tarefa.class);
    }
    
    EntityManager em = JPAUtil.getEntityManager();

    public List<Tarefa> listarPorUsuario(Long usuarioId) {

        List<Tarefa> lista = em.createQuery(
                "SELECT t FROM Tarefa t WHERE t.owner.id = :usuarioId", Tarefa.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
        em.close();
        return lista;
    }

    public List<Tarefa> listarPorCategoria(Long categoriaId) {

        List<Tarefa> lista = em.createQuery(
                "SELECT t FROM Tarefa t WHERE t.categoria.id = :categoriaId", Tarefa.class)
                .setParameter("categoriaId", categoriaId)
                .getResultList();
        em.close();
        return lista;
    }

    public List<Tarefa> listarPorUsuarioECategoria(Long usuarioId, Long categoriaId) {

        List<Tarefa> lista = em.createQuery(
                "SELECT t FROM Tarefa t WHERE t.owner.id = :usuarioId AND t.categoria.id = :categoriaId", Tarefa.class)
                .setParameter("usuarioId", usuarioId)
                .setParameter("categoriaId", categoriaId)
                .getResultList();
        em.close();
        return lista;
    }
}
