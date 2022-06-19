package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.controller.*;
import com.cifprodolfo.comic_store.services.AuthorServices;
import com.cifprodolfo.comic_store.services.CollectionServices;
import com.cifprodolfo.comic_store.services.ComicServices;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
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
    @FXML
    private Button btnAddElement;
    @FXML
    private Button btnDeleteElement;
    private JButton help = new JButton();
    TableView table = new TableView<>();
    private int IdPanel = 0;


    ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");

    public void initialize(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
            ComicViewController comicController = new ComicViewController(txtSearch);
            fxmlLoader.setController(comicController);
            PanelHome.setCenter(fxmlLoader.load());
            lblTitle.setText(resourceBundle.getString("btnComics"));
            btnAddElement.setVisible(true);
            btnDeleteElement.setVisible(true);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorShowComics"));
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public HomeController(){
        try {

            URL helpURL = this.getClass().getResource("/helpES/help.hs");
            HelpSet helpSet = new HelpSet(null, helpURL);
            HelpBroker browser = helpSet.createHelpBroker();
            browser.enableHelpOnButton(help, "info", helpSet);

        } catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorJavaHelp"));
            alert.showAndWait();
        }
    }

    public void openJavaHelp() {
        help.doClick();
    }

    public void getPanel(ActionEvent actionEvent) {

        try {
            Button button = (Button) actionEvent.getSource();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String namePanel = button.getId();

            switch(namePanel){
                case "btnComics":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
                    ComicViewController comicController = new ComicViewController(txtSearch);
                    fxmlLoader.setController(comicController);
                    IdPanel = 0;
                    lblTitle.setText(resourceBundle.getString("btnComics"));
                    btnAddElement.setVisible(true);
                    btnDeleteElement.setVisible(true);
                    break;
                case "btnCollection":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("collectionView.fxml"), resourceBundle);
                    CollectionViewController collectionController = new CollectionViewController(txtSearch);
                    fxmlLoader.setController(collectionController);
                    IdPanel = 1;
                    lblTitle.setText(resourceBundle.getString("btnCollection"));
                    btnAddElement.setVisible(true);
                    btnDeleteElement.setVisible(true);
                    break;
                case "btnAuthor":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("authorView.fxml"), resourceBundle);
                    AuthorViewController authorController = new AuthorViewController(txtSearch);
                    fxmlLoader.setController(authorController);
                    IdPanel = 2;
                    lblTitle.setText(resourceBundle.getString("btnAuthor"));
                    btnAddElement.setVisible(true);
                    btnDeleteElement.setVisible(true);
                    break;
                case "btnReports":
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("reports.fxml"), resourceBundle);
                    lblTitle.setText(resourceBundle.getString("btnReports"));
                    btnAddElement.setVisible(false);
                    btnDeleteElement.setVisible(false);
                    break;
                default:
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicView.fxml"), resourceBundle);
            }

            PanelHome.setCenter(fxmlLoader.load());
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorShowPanelHome"));
            alert.showAndWait();
            e.printStackTrace();
        }

    }

    public void getPanelAdd(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Stage stage = new Stage();

            switch(IdPanel){
                case 0:
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("newComic.fxml"), resourceBundle);
                    stage.setResizable(true);
                    stage.setMinWidth(1130);
                    stage.setMinHeight(900);
                    stage.setTitle(resourceBundle.getString("titleAddComic"));
                    break;
                case 1:
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("newCollection.fxml"), resourceBundle);
                    stage.setResizable(true);
                    stage.setMinHeight(600);
                    stage.setMinWidth(800);
                    stage.setTitle(resourceBundle.getString("titleAddCollection"));
                    break;
                case 2:
                    fxmlLoader = new FXMLLoader(HomeController.class.getResource("newAuthor.fxml"), resourceBundle);
                    stage.setResizable(false);
                    stage.setTitle(resourceBundle.getString("titleAddAuthor"));
                    break;
                default:{}
            }

            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(PanelHome.getScene().getWindow());

            if(IdPanel == 0){
                ComicDetailsController detailsController = fxmlLoader.<ComicDetailsController>getController();
                detailsController.initData2();
            }

            stage.showAndWait();

        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorShowPanelAdd"));
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
            alert.setContentText(resourceBundle.getString("textErrorShowPanelConfiguration"));
            alert.showAndWait();
        }
    }

    public void getDeleteSelectedMethod() {

        switch(IdPanel){
            case 0:
                getComic();
                break;
            case 1:
                getCollection();
                break;
            case 2:
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
            alert.setContentText(resourceBundle.getString("textErrorDeleteCollection"));
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
            alert.setContentText(resourceBundle.getString("textErrorDeleteAuthor"));
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
            alert.setContentText(resourceBundle.getString("textErrorDeleteComic"));
            alert.showAndWait();
        }
    }
}