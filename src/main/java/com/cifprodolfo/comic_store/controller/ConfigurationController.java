package com.cifprodolfo.comic_store.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigurationController {

    @FXML
    private VBox PanelFrame;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");

    public void initialize(){

    }

    public void getLanguageSpanish(){
        changeLanguaje(new Locale("es", "ES"));
    }

    public void getLanguageGalician(){
        changeLanguaje(new Locale("gl", "ES"));
    }

    public void changeLanguaje(Locale language){

        try {
            if (Locale.getDefault().equals(language)){
                return;
            }
            Locale.setDefault(language);

            //Metodo para cerrar la ventana
            Stage stage = (Stage) this.PanelFrame.getScene().getWindow();
            stage.close();

        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("textErrorChangeIdiom"));
            alert.showAndWait();
        }
    }

}