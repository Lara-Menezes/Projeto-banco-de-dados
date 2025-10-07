package service;

import dao.TarefaDAO;
import dao.UsuarioDAO;
import dao.CategoriaDAO;
import model.Categoria;
import model.Tarefa;
import model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
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

        if(tarefa.getPrazo().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Data inválida");
        }

        tarefa.setOwner(usuario);
        tarefa.setCategoria(categoria);
        tarefaDAO.salvar(tarefa);
    }

    public Tarefa buscarTarefa(Long id) {
        return tarefaDAO.buscarPorId(id);
    }

    public List<Tarefa> listarTarefas() {
        List<Tarefa> tarefas = tarefaDAO.listarTodos();
        List<Tarefa> tarefa = new ArrayList<>();
        for (Tarefa t : tarefas) {
            Tarefa trf = new Tarefa();
            trf.setTitulo(t.getTitulo());
            trf.setDescricao(t.getDescricao());
            trf.setId(t.getId());
            trf.setOwner(t.getOwner());
            trf.setCategoria(t.getCategoria());
            trf.setPrazo(t.getPrazo());

            tarefa.add(trf);
//            System.out.printf(
//                    "Tarefa: %-20s | Descrição: %-30s |id: %-5s | Usuário: %-15s | Categoria: %-10s | Prazo: %s%n",
//                    t.getTitulo(),
//                    t.getDescricao(),
//                    t.getId(),
//                    t.getOwner().getNome(),
//                    t.getCategoria().getNome(),
//                    t.getPrazo()
//            );
        }
        return tarefa;
    }

    public void atualizarTarefa(Tarefa tarefa) {
        tarefaDAO.atualizar(tarefa);
    }

    public void excluirTarefa(Long id) {
        tarefaDAO.excluir(id);
    }
   
    public void concluirTarefa(Long id) {
        Tarefa tarefa = tarefaDAO.buscarPorId(id);
        if (tarefa == null) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
        tarefa.setConcluida(true);
        tarefaDAO.atualizar(tarefa);
    }
}
