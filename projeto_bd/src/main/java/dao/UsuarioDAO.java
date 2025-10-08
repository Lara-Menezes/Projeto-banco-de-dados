package dao;

import model.Usuario;

/*
 * Classe responsável por extender os comportamentos genéricos do dao genérico para persistir os usuários
 */

public class UsuarioDAO extends GenericDAO<Usuario> {
    public UsuarioDAO() {
        super(Usuario.class);
    }
}
