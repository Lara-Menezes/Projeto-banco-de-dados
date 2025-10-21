package dao;

import java.util.List;
import model.Usuario;
import jakarta.persistence.EntityManager;

/*
 * Classe responsável por extender os comportamentos genéricos do dao genérico para persistir os usuários
 */

public class UsuarioDAO extends GenericDAO<Usuario> {
    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    @Override
    public List<Usuario> listarTodos() {
    	EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM Usuario u ORDER BY u.nome", Usuario.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
