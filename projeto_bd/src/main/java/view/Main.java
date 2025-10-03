package view;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // registra o stage primeiro (ScreenManager usa depois)
        ScreenManager.setStage(stage);

        // ícone (coloque /view/images/logo.png em resources)
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/view/logo.png")));

        stage.setResizable(false);
        stage.setTitle("To do Check");

        // abre a cena inicial via ScreenManager (ele também adiciona o CSS)
        ScreenManager.changeScene("menu.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
