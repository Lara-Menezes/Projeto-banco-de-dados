package service;

import dao.*;
import model.*;
import java.time.LocalDate;
import java.util.List;

public class TarefaService {

    private final TarefaDAO tarefaDAO = new TarefaDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void criarTarefa(Long usuarioId, Long categoriaId, Tarefa tarefa) {
        Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
        if (usuario == null) throw new IllegalArgumentException("Usuário não encontrado.");

        Categoria categoria = categoriaDAO.buscarPorId(categoriaId);
        if (categoria == null) throw new IllegalArgumentException("Categoria não encontrada.");

        if (tarefa.getPrazo().isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Data inválida: o prazo não pode ser anterior à data atual.");

        tarefa.setOwner(usuario);
        tarefa.setCategoria(categoria);
        tarefaDAO.salvar(tarefa);
    }

    public void atualizarTarefa(Tarefa tarefa) {
        tarefaDAO.atualizar(tarefa);
    }

    public void excluirTarefa(Long id) {
        tarefaDAO.excluir(id);
    }

    public void concluirTarefa(Long id) {
        Tarefa tarefa = tarefaDAO.buscarPorId(id);
        if (tarefa == null) throw new IllegalArgumentException("Tarefa não encontrada.");
        tarefa.setConcluida(true);
        tarefaDAO.atualizar(tarefa);
    }

    public List<Tarefa> listarTarefasPorUsuarioECategoria(Long usuarioId, Long categoriaId) {
        return tarefaDAO.listarPorUsuarioECategoria(usuarioId, categoriaId);
    }
}
