package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.HomeController;
import com.cifprodolfo.comic_store.services.AuthorListServices;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ResourceBundle;
import java.util.function.Predicate;


public class AuthorViewController {

    @FXML
    private TableView tableAuthor;
    @FXML
    private TableColumn<AuthorAdapter, String> lblImageAuthor;
    @FXML
    private TableColumn<AuthorAdapter, String> lblNameAuthor;
    @FXML
    private TableColumn<AuthorAdapter, String> lblSurnameAuthor;
    private TextField txtSearch = new TextField();

    public AuthorViewController(TextField search) {
        this.txtSearch = search;
    }

    private Predicate<AuthorAdapter> searchComics (){
        return order -> {
            if (txtSearch.getText() == null || txtSearch.getText().isEmpty()) return true;
            return searchComic(order, txtSearch.getText());
        };
    }

    private static boolean searchComic(AuthorAdapter comic, String searchText) {
        return (comic.getName().startsWith(searchText));
    }

    public void initialize(){

        ObservableList<AuthorAdapter> data = AuthorListServices.getDataAuthors();;
        FilteredList<AuthorAdapter> searchData = new FilteredList<>(FXCollections.observableList(data));


        lblImageAuthor.setCellValueFactory(new PropertyValueFactory<AuthorAdapter, String>("image"));
        lblNameAuthor.setCellValueFactory(new PropertyValueFactory<AuthorAdapter, String>("name"));
        lblSurnameAuthor.setCellValueFactory(new PropertyValueFactory<AuthorAdapter, String>("surname"));

        tableAuthor.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {searchData.setPredicate(searchComics());});
        SortedList<AuthorAdapter> sortedData = new SortedList<>(searchData);
        tableAuthor.setItems(sortedData);
        sortedData.comparatorProperty().bind(tableAuthor.comparatorProperty());
    }

    public void doubleClickButton(){
        tableAuthor.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 2){
                    TablePosition tablePosition = (TablePosition) tableAuthor.getSelectionModel().getSelectedCells().get(0);
                    int row = tablePosition.getRow();
                    AuthorAdapter item = (AuthorAdapter) tableAuthor.getItems().get(row);
                    getPanelDetailsShow(item);
                }
            }
        });
    }

    public void getPanelDetailsShow(AuthorAdapter authorAdapter){

        try {

            ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
            FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("authorDetails.fxml"), resourceBundle);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Autor");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tableAuthor.getScene().getWindow());

            AuthorDetailsController detailsController = fxmlLoader.<AuthorDetailsController>getController();
            detailsController.initData(authorAdapter);

            stage.showAndWait();
        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al mostrar el comic");
            alert.showAndWait();
        }
    }

}
