package view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.ScreenManager;

public class MenuPrincipalController {

    @FXML private Button btnUsuarios;
    @FXML private Button btnCategorias;
    @FXML private Button btnTarefas;
    @FXML private Button btnAjuda;

    @FXML
    public void initialize() {
        btnUsuarios.setOnAction(e -> {
            try { ScreenManager.changeScene("usuario.fxml"); }
            catch (Exception ex) { ex.printStackTrace(); }
        });

        btnCategorias.setOnAction(e -> {
            try { ScreenManager.changeScene("categoria.fxml"); }
            catch (Exception ex) { ex.printStackTrace(); }
        });

        btnTarefas.setOnAction(e -> {
            try { ScreenManager.changeScene("tarefa.fxml"); }
            catch (Exception ex) { ex.printStackTrace(); }
        });

        btnAjuda.setOnAction(e -> System.out.println("Ajuda clicada"));
    }
}
