package com.cifprodolfo.comic_store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private VBox PanelFrame;

    public void getPanelConfiguration(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HomeController.class.getResource("configuration.fxml"));
            VBox voxConfiguration = fxmlLoader.load();

            PanelFrame.getChildren().add(voxConfiguration);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}