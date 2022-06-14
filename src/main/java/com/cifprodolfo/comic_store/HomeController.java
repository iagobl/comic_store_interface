package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.services.GetCollectionList;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.help.HelpBroker;
import javafx.event.ActionEvent;
import javax.help.HelpSet;
import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private BorderPane PanelHome;
    @FXML
    private Label lblTitle;
    private JButton help = new JButton();
    TableView table = new TableView<>();


    public void initialize(){
    }

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
                case "btnAuthor":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("authorView.fxml"), resourceBundle);
                    lblTitle.setText("Actores");
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
            e.printStackTrace();
        }

    }

    public void getPanelAdd(){

        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Stage stage = new Stage();
            String panelName = lblTitle.getText();

            switch(panelName){
                case "Comics":
                    //fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
                    //stage.setResizable(true);
                    break;
                case "Colecciones":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("newCollection.fxml"), resourceBundle);
                    stage.setResizable(true);
                    break;
                case "Actores":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("newAuthor.fxml"), resourceBundle);
                    stage.setResizable(false);
                    break;
                default:{}
            }

            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Añadir " + lblTitle.getText());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(PanelHome.getScene().getWindow());
            stage.showAndWait();

        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al abrir la pestaña de añadir");
            alert.showAndWait();
            e.printStackTrace();
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

                break;
            case "Colecciones":
                deleteCollection();
                break;
            case "Actores":

                break;
            default: {}
        }
    }

    public CollectionAdapter getCollection(){

        table = (TableView) lblTitle.getScene().lookup("#tableCollection");
        TablePosition tablePosition = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        CollectionAdapter item = (CollectionAdapter) table.getItems().get(row);
        return item;
    }

    public void deleteCollection() {

        CollectionAdapter collection = getCollection();

        try {
            HttpClient client = HttpClient.newHttpClient();
            String deleteCollection = "http://localhost:8080/api-spring/collection/"+collection.getId();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(deleteCollection)).DELETE().build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
            GetCollectionList.updateDataCollections();


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}