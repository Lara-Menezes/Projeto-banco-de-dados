package view.controller;

import controller.TarefaController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Categoria;
import model.Tarefa;
import model.Usuario;
import service.CategoriaService;
import service.UsuarioService;
import view.ScreenManager;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TarefaFXController {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtPrazo;
    @FXML private ComboBox<Usuario> cbUsuario;
    @FXML private ComboBox<Categoria> cbCategoria;

    @FXML private Button btnSalvar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnExcluir;
    @FXML private Button btnConcluir;
    @FXML private Button btnListar;
    @FXML private Button btnVoltar;

    @FXML private Label lblMensagem;

    @FXML private TableView<Tarefa> tabelaTarefas;
    @FXML private TableColumn<Tarefa, String> colTitulo;
    @FXML private TableColumn<Tarefa, String> colDescricao;
    @FXML private TableColumn<Tarefa, String> colPrazo;
    @FXML private TableColumn<Tarefa, Boolean> colConcluida;

    private final TarefaController tarefaController = new TarefaController();
    private final UsuarioService usuarioService = new UsuarioService();
    private final CategoriaService categoriaService = new CategoriaService();

    private Long tarefaSelecionadaId = null;

    @FXML
    public void initialize() {
        // Inicializa os botões
        btnSalvar.setOnAction(e -> salvarTarefa());
//        btnAtualizar.setOnAction(e -> atualizarTarefa()); para quando for implementar a atualização da tarefa
//        btnExcluir.setOnAction(e -> excluirTarefa()); para quando for implementar a remoção da tarefa
//        btnConcluir.setOnAction(e -> concluirTarefa()); para quando for implementar a conclusão da tarefa 
        btnListar.setOnAction(e -> listarTarefas());
        btnVoltar.setOnAction(e -> voltar());

        // Preenche combo boxes
        cbUsuario.getItems().addAll(usuarioService.listarUsuarios());
        cbCategoria.getItems().addAll(categoriaService.listarCategorias());

        configurarComboBoxes();
        configurarTabela();
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

    private void salvarTarefa() {
        String titulo = txtTitulo.getText();
        String descricao = txtDescricao.getText();
        String prazoStr = txtPrazo.getText();
        Usuario usuario = cbUsuario.getValue();
        Categoria categoria = cbCategoria.getValue();

        if (usuario == null || categoria == null) {
            mostrarMensagem("Selecione usuário e categoria!");
            return;
        }else {
            String resultado = tarefaController.salvarTarefa(titulo, descricao, prazoStr, false, usuario, categoria);
            mostrarMensagem(resultado);
            if (resultado.contains("Tarefa criada com sucesso!")) {
                limparCampos();
                listarTarefas();
            }
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
        txtPrazo.setText(tarefa.getPrazo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        cbUsuario.setValue(tarefa.getOwner());
        cbCategoria.setValue(tarefa.getCategoria());
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtDescricao.clear();
        txtPrazo.clear();
        cbUsuario.getSelectionModel().clearSelection();
        cbCategoria.getSelectionModel().clearSelection();
        tarefaSelecionadaId = null;
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
