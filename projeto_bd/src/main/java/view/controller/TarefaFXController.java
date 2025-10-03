package view.controller;

import controller.TarefaController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Categoria;
import model.Usuario;
import service.CategoriaService;
import service.UsuarioService;
import view.ScreenManager;

public class TarefaFXController {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtPrazo;
    @FXML private ComboBox<Usuario> cbUsuario;  
    @FXML private ComboBox<Categoria> cbCategoria; 

    @FXML private Button btnSalvar;
    @FXML private Button btnVoltar;   
    @FXML private Label lblMensagem;

    private final TarefaController tarefaController = new TarefaController();
    private final UsuarioService usuarioService = new UsuarioService();
    private final CategoriaService categoriaService = new CategoriaService();

    @FXML
    public void initialize() {
        btnSalvar.setOnAction(e -> salvarTarefa());
        btnVoltar.setOnAction(e -> voltar()); 

        
        cbUsuario.getItems().addAll(usuarioService.listarUsuarios());
        cbCategoria.getItems().addAll(categoriaService.listarCategorias());

        
        cbUsuario.setCellFactory(param -> new ListCell<Usuario>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
        cbUsuario.setButtonCell(new ListCell<Usuario>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });

        cbCategoria.setCellFactory(param -> new ListCell<Categoria>() {
            @Override
            protected void updateItem(Categoria item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
        cbCategoria.setButtonCell(new ListCell<Categoria>() {
            @Override
            protected void updateItem(Categoria item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
    }

    private void salvarTarefa() {
        try {
            String titulo = txtTitulo.getText();
            String descricao = txtDescricao.getText();
            String prazoStr = txtPrazo.getText();
            boolean concluida = false;

            Usuario usuario = cbUsuario.getValue();
            Categoria categoria = cbCategoria.getValue();

            if (usuario == null || categoria == null) {
                lblMensagem.setStyle("-fx-text-fill: red;");
                lblMensagem.setText("Selecione usuário e categoria!");
                return;
            }

            String resultado = tarefaController.salvarTarefa(
                    titulo, descricao, prazoStr, concluida, usuario, categoria
            );

            if (resultado.contains("sucesso")) {
                lblMensagem.setStyle("-fx-text-fill: green;");
                limparCampos();
            } else {
                lblMensagem.setStyle("-fx-text-fill: red;");
            }

            lblMensagem.setText(resultado);

        } catch (Exception e) {
            lblMensagem.setStyle("-fx-text-fill: red;");
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    
    private void voltar() {
        try {
            ScreenManager.changeScene("menu.fxml"); 
        } catch (Exception e) {
            lblMensagem.setStyle("-fx-text-fill: red;");
            lblMensagem.setText("Erro ao voltar: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtDescricao.clear();
        txtPrazo.clear();
        cbUsuario.getSelectionModel().clearSelection();
        cbCategoria.getSelectionModel().clearSelection();
    }
}
