package com.cifprodolfo.comic_store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("es"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home.fxml"), resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 900);
        stage.setTitle("Tienda de comics");
        stage.setScene(scene);
        stage.show();
    }

    public static void main() {
        launch();
    }
}