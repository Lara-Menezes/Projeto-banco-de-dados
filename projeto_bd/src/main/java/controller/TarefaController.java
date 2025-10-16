package controller;

import service.TarefaService;
import model.Tarefa;
import dto.TarefaDTO;
import java.util.List;

/*
 * TarefaController chama o TarefaService para executar a lógica de negócio de tarefas;
 * Recebe DTOs da interface, validações simples e retorna mensagens de sucesso ou erro;
 * Não manipula o banco diretamente; delega operações como salvar, atualizar, excluir ou concluir ao Service.
 */

public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    public String salvarTarefa(TarefaDTO dto) {
        try {
            tarefaService.criarTarefa(dto);
            return "Tarefa cadastrada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao salvar tarefa: " + e.getMessage();
        }
    }

    public String atualizarTarefa(Integer id, TarefaDTO dto) {
        try {
            tarefaService.atualizarTarefa(id, dto);
            return "Tarefa atualizada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar tarefa: " + e.getMessage();
        }
    }

    public String excluirTarefa(Integer id) {
        try {
            tarefaService.excluirTarefa(id);
            return "Tarefa excluída com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao excluir tarefa: " + e.getMessage();
        }
    }

    public String concluirTarefa(Integer id, boolean status) {
        try {
            tarefaService.concluirTarefa(id, status);
            return "Tarefa concluída com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao concluir tarefa: " + e.getMessage();
        }
    }

    public List<Tarefa> listarTarefasPorUsuarioECategoria(Integer usuarioId, Integer categoriaId) {
        return tarefaService.listarTarefasPorUsuarioECategoria(usuarioId, categoriaId);
    }
    
    public List<Tarefa> listarTarefasPorUsuario(Integer usuarioId) {
        return tarefaService.listarTarefasPorUsuario(usuarioId);
    }
    
    public List<Tarefa> listarTarefasPorCategoria(Integer categoriaId) {
        return tarefaService.listarTarefasPorCategoria(categoriaId);
    }
    
    public List<Tarefa> listarTodasTarefas() {
        return tarefaService.listarTodasTarefas();
    }
}
