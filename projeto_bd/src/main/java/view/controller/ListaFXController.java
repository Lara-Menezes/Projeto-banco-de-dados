package view.controller;

import controller.TarefaController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Tarefa;
import view.ScreenManager;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class ListaFXController {

    @FXML private TableView<Tarefa> tabelaTarefas;
    @FXML private TableColumn<Tarefa, String> colTitulo;
    @FXML private TableColumn<Tarefa, String> colDescricao;
    @FXML private TableColumn<Tarefa, String> colPrazo;
    @FXML private TableColumn<Tarefa, String> colUsuario;
    @FXML private TableColumn<Tarefa, String> colCategoria;
    @FXML private Button btnVoltar;
    @FXML private Label lblMensagem;

    private final TarefaController tarefaController = new TarefaController();


    @FXML
    public void initialize() {
        colTitulo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitulo()));
        colDescricao.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));
        colPrazo.setCellValueFactory(data -> {
            if (data.getValue().getPrazo() != null) {
                return new SimpleStringProperty(data.getValue().getPrazo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            } else {
                return new SimpleStringProperty("");
            }
        });
        colUsuario.setCellValueFactory(data -> {
            if (data.getValue().getOwner() != null) {
                return new SimpleStringProperty(data.getValue().getOwner().getNome());
            } else {
                return new SimpleStringProperty("");
            }
        });
        colCategoria.setCellValueFactory(data -> {
            if (data.getValue().getCategoria() != null) {
                return new SimpleStringProperty(data.getValue().getCategoria().getNome());
            } else {
                return new SimpleStringProperty("");
            }
        });


        carregarTarefas();


        btnVoltar.setOnAction(e -> voltar());
    }

    private void carregarTarefas() {
        List<Tarefa> tarefas = tarefaController.listarTarefas();
        tabelaTarefas.getItems().setAll(tarefas);
    }
    private void voltar() {
        try {
            ScreenManager.changeScene("menu.fxml");
        } catch (Exception e) {
            lblMensagem.setStyle("-fx-text-fill: red;");
            lblMensagem.setText("Erro ao voltar: " + e.getMessage());
        }
    }
}
