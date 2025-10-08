package service;

import dao.UsuarioDAO;
import model.Usuario;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void criarUsuario(Usuario usuario) {
        usuarioDAO.salvar(usuario);
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioDAO.buscarPorId(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarTodos();
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
    }

    public void excluirUsuario(Long id) {
        usuarioDAO.excluir(id);
    }
}
