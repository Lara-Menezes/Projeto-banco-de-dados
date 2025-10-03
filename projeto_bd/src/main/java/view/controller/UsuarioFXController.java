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
    @FXML private Button btnUsuarios;
    @FXML private Button btnVoltar;
    @FXML private Label lblMensagem;

    private final UsuarioController usuarioController = new UsuarioController();

    @FXML
    public void initialize() {
        btnSalvar.setOnAction(e -> salvarUsuario());
        btnVoltar.setOnAction(this::voltar);    
        btnUsuarios.setOnAction(this::abrirUsuarios); 
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


    private void voltar(ActionEvent event) {
        try {
            ScreenManager.changeScene("menu.fxml"); 
        } catch (Exception e) {
            e.printStackTrace();
            lblMensagem.setText("Erro ao carregar tela de menu!");
        }
    }

    
    private void abrirUsuarios(ActionEvent event) {
        try {
            ScreenManager.changeScene("usuarioList.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            lblMensagem.setText("Erro ao carregar lista de usuários!");
        }
    }

}
