package com.cifprodolfo.comic_store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private VBox PanelFrame;
    @FXML
    private Label lblTitle;
    private JButton help = new JButton();

    public HomeController(){
        try {
            URL helpURL = this.getClass().getResource("/helpES/help.hs");
            HelpSet helpSet = new HelpSet(null, helpURL);
            HelpBroker browser = helpSet.createHelpBroker();
            browser.enableHelpOnButton(help, "manual", helpSet);

        } catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al abrir el javahelp");
            alert.showAndWait();
        }
    }

    public void openJavaHelp() {
        help.doClick();
    }

    public void getPanelConfiguration(){

        try{
            PanelFrame.getChildren().clear();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HomeController.class.getResource("configuration.fxml"));
            VBox voxConfiguration = fxmlLoader.load();

            PanelFrame.getChildren().add(voxConfiguration);
            lblTitle.setText("Configuración");
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al abrir la configuración");
            alert.showAndWait();
        }
    }

    public void getPanelReport() {

        try {
            PanelFrame.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HomeController.class.getResource("reports.fxml"));
            VBox voxConfiguration = fxmlLoader.load();

            PanelFrame.getChildren().add(voxConfiguration);
            lblTitle.setText("Informes");

        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al abrir los informes");
            alert.showAndWait();
        }
    }



}