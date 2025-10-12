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
        	System.err.println("Error: " +  e.getMessage());
            return "Não foi possível salvar a tarefa";
        } catch (Exception e) {
        	System.err.println("Error: " +  e.getMessage());
            return "Não foi possível salvar a tarefa";
        }
    }

    public String atualizarTarefa(Long id, TarefaDTO dto) {
        try {
            tarefaService.atualizarTarefa(id, dto);
            return "Tarefa atualizada com sucesso!";
        } catch (IllegalArgumentException e) {
        	System.err.println("Error: " + e.getMessage());
            return "Não foi possível atualizar a tarefa";
        } catch (Exception e) {
        	System.err.println("Error: " + e.getMessage());
            return "Não foi possível atualizar a tarefa";
        }
    }

    public String excluirTarefa(Long id) {
        try {
            tarefaService.excluirTarefa(id);
            return "Tarefa excluída com sucesso!";
        } catch (IllegalArgumentException e) {
        	System.err.println("Error: " + e.getMessage());
        	return "Não foi possível excluir a tarefa";
        } catch (Exception e) {
        	System.err.println("Error: " + e.getMessage());
            return "Não foi possível excluir a tarefa";
        }
    }

    public String concluirTarefa(Long id, boolean status) {
        try {
            tarefaService.concluirTarefa(id, status);
            return "Tarefa concluída com sucesso!";
        } catch (IllegalArgumentException e) {
        	System.err.println("Error: " + e.getMessage());
            return "Não foi possível concluir a tarefa";
        } catch (Exception e) {
        	System.err.println("Error: " + e.getMessage());
            return "Não foi possível concluir a tarefa";
        }
    }

    public List<Tarefa> listarTarefasPorUsuarioECategoria(Long usuarioId, Long categoriaId) {
        return tarefaService.listarTarefasPorUsuarioECategoria(usuarioId, categoriaId);
    }
    
    public List<Tarefa> listarTodasTarefas() {
        return tarefaService.listarTodasTarefas();
    }
}
