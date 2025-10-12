package view.controller;

import controller.UsuarioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ScreenManager; // importante para trocar de telas

public class UsuarioFXController {

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private Button btnSalvar;
    @FXML private Button btnVoltar;
    @FXML private Label lblMensagem;
    @FXML private TextField txtId;
    @FXML private Button btnAtualizar;

    private final UsuarioController usuarioController = new UsuarioController();

    @FXML
    public void initialize() {
        btnSalvar.setOnAction(e -> salvarUsuario());
        btnVoltar.setOnAction(this::voltar);    
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
    	} 
    	catch(NumberFormatException e) {
    		lblMensagem.setText("ID inválido");
    	} 
    	catch (Exception e) {
    		e.printStackTrace();
    		lblMensagem.setText("Erro ao atualizar o usuário");
    	}
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
