package dao;

import jakarta.persistence.EntityManager;
import model.Tarefa;

import java.util.List;

public class TarefaDAO extends GenericDAO<Tarefa> {
    EntityManager em = JPAUtil.getEntityManager();
    public TarefaDAO() {
        super(Tarefa.class);
    }

    @Override
    public List<Tarefa> listarTodos(){
       try{
           return em.createQuery(
                   "SELECT t FROM Tarefa t " +
                           "JOIN FETCH t.owner " +
                           "JOIN FETCH t.categoria",
                   Tarefa.class
           ).getResultList();
       } finally {
           em.close();
       }
    }
}
