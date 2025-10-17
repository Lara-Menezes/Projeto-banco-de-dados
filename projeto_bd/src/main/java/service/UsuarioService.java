package service;

import dao.UsuarioDAO;
import dao.TarefaDAO;
import dao.CategoriaDAO;
import dto.UsuarioDTO;
import model.Usuario;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final TarefaService tarefaService = new TarefaService(
            new TarefaDAO(),
            new UsuarioDAO(),
            new CategoriaDAO()
    );

    public void criarUsuario(UsuarioDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank())
            throw new IllegalArgumentException("O nome do usuário não pode estar vazio.");

        if (dto.getEmail() == null || dto.getEmail().isBlank())
            throw new IllegalArgumentException("O e-mail do usuário não pode estar vazio.");

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        usuarioDAO.salvar(usuario);
    }

    public void atualizarUsuario(Integer id, UsuarioDTO dto) {
        Usuario usuario = usuarioDAO.buscarPorId(id);
        if (usuario == null)
            throw new IllegalArgumentException("Usuário não encontrado.");

        if (dto.getNome() == null || dto.getNome().isBlank())
            throw new IllegalArgumentException("O nome do usuário não pode estar vazio.");

        if (dto.getEmail() == null || dto.getEmail().isBlank())
            throw new IllegalArgumentException("O e-mail do usuário não pode estar vazio.");

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        usuarioDAO.atualizar(usuario);
    }

    public void excluirUsuario(Integer id) {
        Usuario usuario = usuarioDAO.buscarPorId(id);
        if (usuario == null)
            throw new IllegalArgumentException("Usuário não encontrado.");

        tarefaService.deletarPorUsuario(id);
        usuarioDAO.excluir(id);
    }
    
    public Usuario buscarUsuario(Integer id) {
        return usuarioDAO.buscarPorId(id);
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        return usuarios;
    }
}
