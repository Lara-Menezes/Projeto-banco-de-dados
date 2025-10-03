package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/application/logo.png")));
        stage.setResizable(false);
        stage.setTitle("To do Check");
        stage.setScene(scene);

        // IMPORTANTE: registra o stage no ScreenManager
        ScreenManager.setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
