package view.controller;

import controller.TarefaController;
import dao.CategoriaDAO;
import dao.TarefaDAO;
import dao.UsuarioDAO;
import dto.TarefaDTO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Categoria;
import model.Tarefa;
import model.Usuario;
import service.CategoriaService;
import service.TarefaService;
import service.UsuarioService;
import view.ScreenManager;

import java.time.LocalDate;
import java.util.List;

public class TarefaFXController {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtDescricao;
    @FXML private DatePicker dpPrazo;
    @FXML private ComboBox<Usuario> cbUsuario;
    @FXML private ComboBox<Categoria> cbCategoria;

    @FXML private Button btnSalvar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnExcluir;
    @FXML private Button btnConcluir;
    @FXML private Button btnListar;
    @FXML private Button btnVoltar;
    @FXML private Button btnlimpar;

    @FXML private Label lblMensagem;

    @FXML private TableView<Tarefa> tabelaTarefas;
    @FXML private TableColumn<Tarefa, String> colTitulo;
    @FXML private TableColumn<Tarefa, String> colDescricao;
    @FXML private TableColumn<Tarefa, LocalDate> colPrazo;
    @FXML private TableColumn<Tarefa, Boolean> colConcluida;

    private final UsuarioService usuarioService = new UsuarioService();
    private final CategoriaService categoriaService = new CategoriaService();
    private final TarefaController tarefaController;

    private Long tarefaSelecionadaId = null;

    public TarefaFXController() {
        TarefaDAO tarefaDAO = new TarefaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        TarefaService tarefaService = new TarefaService(tarefaDAO, usuarioDAO, categoriaDAO);
        this.tarefaController = new TarefaController(tarefaService);
    }

    @FXML
    public void initialize() {
        // Inicializa os botões
        btnSalvar.setOnAction(e -> salvarTarefa());
        btnAtualizar.setOnAction(e -> atualizarTarefa());
        btnExcluir.setOnAction(e -> excluirTarefa());
        btnConcluir.setOnAction(e -> concluirTarefa());
        btnListar.setOnAction(e -> listarTarefas());
        btnVoltar.setOnAction(e -> voltar());
        btnlimpar.setOnAction(e -> limparCampos());

        // Preenche combo boxes
        cbUsuario.getItems().addAll(usuarioService.listarUsuarios());
        cbCategoria.getItems().addAll(categoriaService.listarCategorias());

        configurarComboBoxes();
        configurarTabela();
        carregarTarefas();
        tabelaTarefas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void configurarComboBoxes() {
        cbUsuario.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
        cbUsuario.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });

        cbCategoria.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Categoria item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
        cbCategoria.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Categoria item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
    }

    private void configurarTabela() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colPrazo.setCellValueFactory(new PropertyValueFactory<>("prazo"));
        colConcluida.setCellValueFactory(new PropertyValueFactory<>("concluida"));

        tabelaTarefas.setOnMouseClicked(event -> {
            Tarefa selecionada = tabelaTarefas.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                preencherCampos(selecionada);
                tarefaSelecionadaId = selecionada.getId();
            }
        });
    }

    private void carregarTarefas() {
        List<Tarefa> tarefas = tarefaController.listarTodasTarefas();
        tabelaTarefas.setItems(FXCollections.observableArrayList(tarefas));
    }

    private void salvarTarefa() {
        Usuario usuario = cbUsuario.getValue();
        Categoria categoria = cbCategoria.getValue();

        if (usuario == null || categoria == null) {
            mostrarMensagem("Selecione usuário e categoria!");
            return;
        }

        if (dpPrazo.getValue() == null) {
            mostrarMensagem("Selecione uma data para o prazo!");
            return;
        }
        
        if(dpPrazo.getValue().isBefore(LocalDate.now())) {
        	mostrarMensagem("Coloque uma data futura. considere o prazo a ser realizado!");
        }

        TarefaDTO dto = new TarefaDTO();
        dto.setTitulo(txtTitulo.getText());
        dto.setDescricao(txtDescricao.getText());
        dto.setPrazo(dpPrazo.getValue());
        dto.setConcluida(false);
        dto.setUsuarioId(usuario.getId());
        dto.setCategoriaId(categoria.getId());

        String resultado = tarefaController.salvarTarefa(dto);
        mostrarMensagem(resultado);

        if (resultado.contains("sucesso")) {
            limparCampos();
            carregarTarefas();
        }
    }

    private void atualizarTarefa() {
        if (tarefaSelecionadaId == null) {
            mostrarMensagem("Selecione uma tarefa para atualizar!");
            return;
        }

        if (dpPrazo.getValue() == null) {
            mostrarMensagem("Selecione uma data para o prazo!");
            return;
        }
        
        if(dpPrazo.getValue().isBefore(LocalDate.now())) {
        	mostrarMensagem("Coloque uma data futura, leve em conta o prazo a ser realizado!");
        }

        TarefaDTO dto = new TarefaDTO();
        dto.setTitulo(txtTitulo.getText());
        dto.setDescricao(txtDescricao.getText());
        dto.setPrazo(dpPrazo.getValue());
        dto.setConcluida(false);
        dto.setUsuarioId(cbUsuario.getValue().getId());
        dto.setCategoriaId(cbCategoria.getValue().getId());

        String resultado = tarefaController.atualizarTarefa(tarefaSelecionadaId, dto);
        mostrarMensagem(resultado);

        if (resultado.contains("sucesso")) {
            limparCampos();
            carregarTarefas();
        }
    }

    private void excluirTarefa() {
        if (tarefaSelecionadaId == null) {
            mostrarMensagem("Selecione uma tarefa para excluir!");
            return;
        }

        String resultado = tarefaController.excluirTarefa(tarefaSelecionadaId);
        mostrarMensagem(resultado);

        if (resultado.contains("sucesso")) {
            limparCampos();
            carregarTarefas();
        }
    }

    private void concluirTarefa() {
        if (tarefaSelecionadaId == null) {
            mostrarMensagem("Selecione uma tarefa para concluir!");
            return;
        }

        String resultado = tarefaController.concluirTarefa(tarefaSelecionadaId, true);
        mostrarMensagem(resultado);

        if (resultado.contains("sucesso")) {
            limparCampos();
            carregarTarefas();
        }
    }

    private void listarTarefas() {
        Usuario usuario = cbUsuario.getValue();
        Categoria categoria = cbCategoria.getValue();

        if (usuario == null || categoria == null) {
            mostrarMensagem("Selecione usuário e categoria para listar!");
            return;
        }

        List<Tarefa> tarefas = tarefaController.listarTarefasPorUsuarioECategoria(usuario.getId(), categoria.getId());
        tabelaTarefas.setItems(FXCollections.observableArrayList(tarefas));
    }

    private void preencherCampos(Tarefa tarefa) {
        txtTitulo.setText(tarefa.getTitulo());
        txtDescricao.setText(tarefa.getDescricao());
        dpPrazo.setValue(tarefa.getPrazo());
        cbUsuario.setValue(tarefa.getOwner());
        cbCategoria.setValue(tarefa.getCategoria());
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtDescricao.clear();
        dpPrazo.setValue(null);

        cbUsuario.getSelectionModel().clearSelection();
        cbUsuario.setValue(null);

        cbCategoria.getSelectionModel().clearSelection();
        cbCategoria.setValue(null);

        tarefaSelecionadaId = null;
        carregarTarefas();
        
    }

    private void voltar() {
        try {
            ScreenManager.changeScene("menu.fxml");
        } catch (Exception e) {
            mostrarMensagem("Erro ao voltar: " + e.getMessage());
        }
    }

    private void mostrarMensagem(String mensagem) {
        lblMensagem.setText(mensagem);
        lblMensagem.setStyle(mensagem.contains("sucesso") ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
    }
}
