package view.controller;

import controller.UsuarioController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Tarefa;
import model.Usuario;
import view.ScreenManager; // importante para trocar de telas

import java.util.List;

public class UsuarioFXController {

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private Button btnSalvar;
    @FXML private Button btnVoltar;
    @FXML private Label lblMensagem;
    @FXML private TextField txtId;
    @FXML private Button btnAtualizar;
    @FXML private Button btnListar;
    @FXML private Button btnlimpar;
    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<Usuario, Long> colId;
    @FXML private TableColumn<Usuario, String> colNome;
    @FXML private TableColumn<Usuario, String> colEmail;

    private final UsuarioController usuarioController = new UsuarioController();


    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        btnSalvar.setOnAction(e -> salvarUsuario());
        btnVoltar.setOnAction(this::voltar);
        btnListar.setOnAction(e -> listar());
        btnlimpar.setOnAction(e -> limparCampos());
        
        tabelaUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    private void salvarUsuario() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();

        String resultado = usuarioController.salvarUsuario(nome, email);

        if (resultado.contains("sucesso")) {
            lblMensagem.setStyle("-fx-text-fill: green;");
            txtNome.clear();
            txtEmail.clear();
        } else {
            lblMensagem.setStyle("-fx-text-fill: red;");
        }

        lblMensagem.setText(resultado);
    }
    @FXML
    private void deletar(){
        try{
            String idText = txtId.getText();
            if(idText == null || idText.isBlank()){
                lblMensagem.setText("Informe o id do usuário para deletar.");
                return;
            }
            Long id = Long.parseLong(idText);
            usuarioController.deletarUsuario(id);
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void atualizar(ActionEvent event) {
    	try {
    		String idText = txtId.getText();
    		if(idText == null || idText.isBlank()) {
    			lblMensagem.setText("Informe o id do usuário para atualizar.");
    			return;
    		}
    		Long id = Long.parseLong(idText);
    		String nome = txtNome.getText();
    		String email = txtEmail.getText();
    		UsuarioController uc = new UsuarioController();
    		String resultado = uc.atualizarUsuario(id, nome, email);
    		lblMensagem.setText(resultado);
            listar();
    	}
    	catch(NumberFormatException e) {
    		lblMensagem.setText("ID inválido");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		lblMensagem.setText("Erro ao atualizar o usuário");
    	}
    }

    @FXML
    private void listar() {
        List<Usuario> usuarios = usuarioController.ListarUsuarios();
        tabelaUsuarios.setItems(FXCollections.observableArrayList(usuarios));
    }
    
    private void limparCampos() {
        txtNome.clear();
        txtEmail.clear();
        txtId.clear();

    }

    private void voltar(ActionEvent event) {
        try {
            ScreenManager.changeScene("menu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            lblMensagem.setText("Erro ao carregar tela de menu!");
        }
    }
}
