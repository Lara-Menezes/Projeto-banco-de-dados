package service;

import dao.CategoriaDAO;
import dao.TarefaDAO;
import dao.UsuarioDAO;
import model.Tarefa;
import model.Usuario;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final TarefaService tarefaService = new TarefaService(
            new TarefaDAO(),
            new UsuarioDAO(),
            new CategoriaDAO()
    );

    public void criarUsuario(Usuario usuario) {
        usuarioDAO.salvar(usuario);
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioDAO.buscarPorId(id);
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        return usuarios;
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
    }

    public void excluirUsuario(Long id) {
        tarefaService.deletarPorUsuario(id);
        usuarioDAO.excluir(id);


    }
}
