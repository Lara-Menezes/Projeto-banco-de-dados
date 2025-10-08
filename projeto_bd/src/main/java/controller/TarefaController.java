package controller;

import model.*;
import service.TarefaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
 * Essa classe é responsável por controllar a regra de negócio (service, dao) pedindo requisições  para a mesma para salvar
 * listar, excluir, atualizar e concluir uma tarefa. Ela instancia um objetp final tarefaService que tem a lógica de negócio
 * e que chama os daos para obter dados das entidades, por meio disso ela faz a lógica: passando métodos públicos para executar as
 * funções do CRUD. 
 */

public class TarefaController {

    private final TarefaService tarefaService = new TarefaService();

    public String salvarTarefa(String titulo, String descricao, String prazoStr, boolean concluida,
                               Usuario usuario, Categoria categoria) {
        if (titulo == null || titulo.isBlank() ||
            descricao == null || descricao.isBlank() ||
            prazoStr == null || prazoStr.isBlank() ||
            usuario == null || categoria == null) {
            return "Preencha todos os campos!";
        }

        try {
            LocalDate prazo = LocalDate.parse(prazoStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Tarefa tarefa = new Tarefa();
            tarefa.setTitulo(titulo);
            tarefa.setDescricao(descricao);
            tarefa.setPrazo(prazo);
            tarefa.setConcluida(concluida);
            tarefa.setOwner(usuario);
            tarefa.setCategoria(categoria);
            tarefaService.criarTarefa(usuario.getId(), categoria.getId(), tarefa);
            return "Tarefa cadastrada com sucesso!";
        } catch (Exception e) {
            return "Erro ao salvar tarefa: " + e.getMessage();
        }
    }

    public List<Tarefa> listarTarefasPorUsuarioECategoria(Long usuarioId, Long categoriaId) {
        return tarefaService.listarTarefasPorUsuarioECategoria(usuarioId, categoriaId);
    }
}
