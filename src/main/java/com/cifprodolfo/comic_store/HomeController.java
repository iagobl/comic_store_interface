package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.controller.AuthorViewController;
import com.cifprodolfo.comic_store.controller.ComicDetailsController;
import com.cifprodolfo.comic_store.controller.ComicViewController;
import com.cifprodolfo.comic_store.services.AuthorServices;
import com.cifprodolfo.comic_store.services.CollectionServices;
import com.cifprodolfo.comic_store.services.ComicServices;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.help.HelpBroker;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import org.w3c.dom.Text;

import javax.help.HelpSet;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private BorderPane PanelHome;
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtSearch;
    private JButton help = new JButton();
    TableView table = new TableView<>();


    public void initialize(){
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
            FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
            ComicViewController comicController = new ComicViewController(txtSearch);
            fxmlLoader.setController(comicController);
            PanelHome.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al mostrar los comics");
            alert.showAndWait();
            e.printStackTrace();
        }
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
            /*
            ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
            FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
            ComicViewController comicController = new ComicViewController(txtSearch);
            fxmlLoader.setController(comicController);;
            PanelHome.setCenter(fxmlLoader.load());
             */

            switch(namePanel){
                case "btnComics":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
                    ComicViewController comicController = new ComicViewController(txtSearch);
                    fxmlLoader.setController(comicController);
                    lblTitle.setText("Comics");
                    break;
                case "btnCollection":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("collectionView.fxml"), resourceBundle);
                    lblTitle.setText("Colecciones");
                    break;
                case "btnAuthor":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("authorView.fxml"), resourceBundle);
                    AuthorViewController authorController = new AuthorViewController(txtSearch);
                    fxmlLoader.setController(authorController);
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
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("newComic.fxml"), resourceBundle);
                    stage.setResizable(true);
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

            if(lblTitle.getText().equals("Comics")){
                ComicDetailsController detailsController = fxmlLoader.<ComicDetailsController>getController();
                detailsController.initData2();
            }

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
                getComic();
                break;
            case "Colecciones":
                getCollection();
                break;
            case "Actores":
                getAuthors();
                break;
            default: {}
        }
    }

    public void getCollection(){

        try {
            table = (TableView) lblTitle.getScene().lookup("#tableCollection");
            TablePosition tablePosition = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            CollectionAdapter item = (CollectionAdapter) table.getItems().get(row);
            CollectionServices.deleteCollection(item);
        } catch(IOException | InterruptedException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al borrar la colección");
            alert.showAndWait();
        }
    }

    public void getAuthors(){

        try {
            table = (TableView) lblTitle.getScene().lookup("#tableAuthor");
            TablePosition tablePosition = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            AuthorAdapter item = (AuthorAdapter) table.getItems().get(row);
            AuthorServices.deleteAuthor(item);
        } catch(IOException | InterruptedException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al borrar el autor");
            alert.showAndWait();
        }
    }

    public void getComic(){

        try {
            table = (TableView) lblTitle.getScene().lookup("#tableComics");
            TablePosition tablePosition = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            ComicAdapter item = (ComicAdapter) table.getItems().get(row);
            ComicServices.deleteComic(item);
        } catch(IOException | InterruptedException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al borrar el comic");
            alert.showAndWait();
        }
    }




}