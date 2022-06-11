package com.cifprodolfo.comic_store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private BorderPane PanelMain;
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

    public void getPanelReport() {
        getPanel("reports.fxml", "Informes");
    }

    public void getPanel(String namePanel, String titlePanel){

        try {
            PanelFrame.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HomeController.class.getResource(namePanel));
            VBox voxConfiguration = fxmlLoader.load();

            PanelFrame.getChildren().add(voxConfiguration);
            lblTitle.setText(titlePanel);
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al abrir la pestaña");
            alert.showAndWait();
        }
    }

    public void getPanelConfiguration(){

        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
            FXMLLoader fxmlLoader  = new FXMLLoader(HomeController.class.getResource("configuration.fxml"), resourceBundle);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Configuración");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();

            Scene panel = PanelMain.getScene();
            panel.setRoot(FXMLLoader.load(getClass().getResource("home.fxml"),ResourceBundle.getBundle("language/language", Locale.getDefault())));

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al abrir la configuración");
            alert.showAndWait();
        }
    }

}