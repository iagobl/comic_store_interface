package com.cifprodolfo.comic_store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.help.HelpBroker;
import javafx.event.ActionEvent;
import javax.help.HelpSet;
import javax.swing.*;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private BorderPane PanelHome;
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

    public void getPanel(ActionEvent actionEvent) {

        try {
            Button button = (Button) actionEvent.getSource();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
            FXMLLoader fxmlLoader = new FXMLLoader();
            String namePanel = button.getId();

            switch(namePanel){
                case "btnComics":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
                    lblTitle.setText("Comics");
                    break;
                case "btnCollection":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("collectionView.fxml"), resourceBundle);
                    lblTitle.setText("Colecciones");
                    break;
                case "btnReports":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("reports.fxml"), resourceBundle);
                    lblTitle.setText("Informes");
                    break;
                default:
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
            }

            PanelHome.setCenter(fxmlLoader.load());
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al abrir la pestaña");
            alert.showAndWait();
        }

    }

    public void getPanelAdd(){

        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
            FXMLLoader fxmlLoader = new FXMLLoader();
            String panelName = lblTitle.getText();

            switch(panelName){
                case "Comics":
                    //fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
                    break;
                case "Colecciones":
                    //fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
                    break;
                case "Autores":
                    //fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
                    break;
                default:{}
            }

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Añadir " + lblTitle.getText());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(PanelHome.getScene().getWindow());
            stage.showAndWait();

        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al abrir la pestaña de añadir");
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
            stage.initOwner(PanelHome.getScene().getWindow());
            stage.showAndWait();

            Scene panel = PanelHome.getScene();
            panel.setRoot(FXMLLoader.load(getClass().getResource("home.fxml"),ResourceBundle.getBundle("language/language", Locale.getDefault())));

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al abrir la configuración");
            alert.showAndWait();
        }
    }

    public void getDeleteSelectedMethod() {

        String namePanel = lblTitle.getText();

        switch(namePanel){
            case "Comics":
                CollectionViewController.deleteCollection();
                break;
            case "Colecciones":

                break;
            case "Actores":

                break;
            default: {}
        }
    }


}