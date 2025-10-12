package service;

import dao.*;
import model.*;
import dto.TarefaDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TarefaService {

    private final TarefaDAO tarefaDAO;
    private final UsuarioDAO usuarioDAO;
    private final CategoriaDAO categoriaDAO;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public TarefaService(TarefaDAO tarefaDAO, UsuarioDAO usuarioDAO, CategoriaDAO categoriaDAO) {
        this.tarefaDAO = tarefaDAO;
        this.usuarioDAO = usuarioDAO;
        this.categoriaDAO = categoriaDAO;
    }

    public void criarTarefa(TarefaDTO dto) {
        Usuario usuario = usuarioDAO.buscarPorId(dto.getUsuarioId());
        if (usuario == null) throw new IllegalArgumentException("Usuário não encontrado.");

        Categoria categoria = categoriaDAO.buscarPorId(dto.getCategoriaId());
        if (categoria == null) throw new IllegalArgumentException("Categoria não encontrada.");

        LocalDate prazo = dto.getPrazo();
        if (prazo.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Data inválida: o prazo não pode ser anterior à data atual.");

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrazo(prazo);
        tarefa.setConcluida(dto.isConcluida());
        tarefa.setOwner(usuario);
        tarefa.setCategoria(categoria);

        tarefaDAO.salvar(tarefa);
    }

    public void atualizarTarefa(Long id, TarefaDTO dto) {
        Tarefa tarefa = tarefaDAO.buscarPorId(id);
        if (tarefa == null) throw new IllegalArgumentException("Tarefa não encontrada.");

        LocalDate prazo = dto.getPrazo();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrazo(prazo);
        tarefa.setConcluida(dto.isConcluida());

        Usuario usuario = usuarioDAO.buscarPorId(dto.getUsuarioId());
        Categoria categoria = categoriaDAO.buscarPorId(dto.getCategoriaId());
        if (usuario == null || categoria == null)
            throw new IllegalArgumentException("Usuário ou categoria inválidos.");

        tarefa.setOwner(usuario);
        tarefa.setCategoria(categoria);

        tarefaDAO.atualizar(tarefa);
    }

    public void excluirTarefa(Long id) {
        Tarefa tarefa = tarefaDAO.buscarPorId(id);
        if (tarefa == null) throw new IllegalArgumentException("Tarefa não encontrada.");
        tarefaDAO.excluir(id);
    }

    public void concluirTarefa(Long id, boolean status) {
        Tarefa tarefa = tarefaDAO.buscarPorId(id);
        if (tarefa == null) throw new IllegalArgumentException("Tarefa não encontrada.");
        tarefa.setConcluida(status);
        tarefaDAO.atualizar(tarefa);
    }

    public List<Tarefa> listarTarefasPorUsuarioECategoria(Long usuarioId, Long categoriaId) {
        return tarefaDAO.listarPorUsuarioECategoria(usuarioId, categoriaId);
    }
    
    public List<Tarefa> listarTodasTarefas() {
        return tarefaDAO.listarTodos();
    }
}

