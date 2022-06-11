package com.cifprodolfo.comic_store;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.util.Locale;

public class ConfigurationController {

    @FXML
    private VBox PanelFrame;

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
            System.out.println("Hola");
            if (Locale.getDefault().equals(language)){
                return;
            }

            Locale.setDefault(language);

        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al cambiar el idioma");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

}