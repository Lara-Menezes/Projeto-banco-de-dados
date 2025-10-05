package view.controller;

import controller.CategoriaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ScreenManager;

public class CategoriaFXController {

    @FXML private TextField txtNomeCategoria;
    @FXML private Button btnSalvar;
    @FXML private Button btnVoltar;
    @FXML private Label lblMensagem;

    private final CategoriaController categoriaController = new CategoriaController();

    @FXML
    public void initialize() {
        btnSalvar.setOnAction(e -> salvarCategoria());
        btnVoltar.setOnAction(this::voltar);
        btnCategorias.setOnAction(this::abrirCategorias);
    }

    private void salvarCategoria() {
        String nome = txtNomeCategoria.getText();
        String resultado = categoriaController.salvarCategoria(nome);

        if (resultado.contains("sucesso")) {
            lblMensagem.setStyle("-fx-text-fill: green;");
            txtNomeCategoria.clear();
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

    
    private void abrirCategorias(ActionEvent event) {
        try {
            ScreenManager.changeScene("categoriaList.fxml"); 
        } catch (Exception e) {
            e.printStackTrace();
            lblMensagem.setText("Erro ao carregar lista de categorias!");
        }
    }
}
