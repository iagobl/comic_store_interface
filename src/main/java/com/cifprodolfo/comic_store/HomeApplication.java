package com.cifprodolfo.comic_store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        Scene scene = new Scene(fxmlLoader.load(), 1100, 750);
        stage.setTitle(resourceBundle.getString("titleAplication"));
        stage.getIcons().add(new Image(HomeApplication.class.getResourceAsStream("/images/icon_photo.png")));
        stage.setMinHeight(710);
        stage.setMinWidth(980);
        stage.setScene(scene);
        stage.show();
    }

    public static void main() {
        launch();
    }

}