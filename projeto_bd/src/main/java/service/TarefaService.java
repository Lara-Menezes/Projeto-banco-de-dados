package service;

import dao.TarefaDAO;
import dao.UsuarioDAO;
import dao.CategoriaDAO;
import model.Categoria;
import model.Tarefa;
import model.Usuario;

import java.util.List;

public class TarefaService {
    private final TarefaDAO tarefaDAO = new TarefaDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void criarTarefa(Long usuarioId, Long categoriaId, Tarefa tarefa) {
        Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        Categoria categoria = categoriaDAO.buscarPorId(categoriaId);
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        tarefa.setOwner(usuario);
        tarefa.setCategoria(categoria);
        tarefaDAO.salvar(tarefa);
    }

    public Tarefa buscarTarefa(Long id) {
        return tarefaDAO.buscarPorId(id);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaDAO.listarTodos();
    }

    public List<Tarefa> listarTarefasPorUsuario(Long usuarioId) {
        return tarefaDAO.listarPorUsuario(usuarioId);
    }

    public List<Tarefa> listarTarefasPorCategoria(Long categoriaId) {
        return tarefaDAO.listarPorCategoria(categoriaId);
    }

    public void atualizarTarefa(Tarefa tarefa) {
        tarefaDAO.atualizar(tarefa);
    }

    public void excluirTarefa(Long id) {
        tarefaDAO.excluir(id);
    }
}
