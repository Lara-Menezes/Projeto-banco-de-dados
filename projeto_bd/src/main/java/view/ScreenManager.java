package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ScreenManager {
    private static Stage stage;

    public static void setStage(Stage s) {
        stage = s;
    }

    public static void changeScene(String fxml) throws Exception {
        String path = "/view/" + fxml;
        URL resource = ScreenManager.class.getResource(path);

        if (resource == null) {
            throw new RuntimeException("FXML não encontrado: " + path);
        }

        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);

        // 🔹 Guarda o tamanho atual da janela antes de trocar de cena
        double width = stage.getWidth();
        double height = stage.getHeight();
        boolean maximized = stage.isMaximized();

        // 🔹 Aplica a nova cena
        stage.setScene(scene);

        // 🔹 Restaura o tamanho original ou estado maximizado
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setMaximized(maximized);

        stage.show();
    }


}
