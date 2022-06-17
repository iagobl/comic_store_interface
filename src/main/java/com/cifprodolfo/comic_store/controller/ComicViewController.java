package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.HomeController;
import com.cifprodolfo.comic_store.model.Comic;
import com.cifprodolfo.comic_store.services.ComicListServices;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.beans.EventHandler;
import java.util.ResourceBundle;
import java.util.function.Predicate;


public class ComicViewController {

    @FXML
    private StackPane stackPaneComics;
    @FXML
    private TableView tableComics;
    @FXML
    private TableColumn<ComicAdapter, String> lblImageComic;
    @FXML
    private TableColumn<ComicAdapter, String> lblNameComic;
    @FXML
    private TableColumn<ComicAdapter, Integer> lblNumberComic;
    @FXML
    private TableColumn<ComicAdapter, Integer> lblPagesComic;
    @FXML
    private TableColumn<ComicAdapter, Integer> lblPublicationComic;
    private TextField txtSearch = new TextField();

    ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");

    public ComicViewController(TextField text){
        this.txtSearch = text;
    }

    public void initialize(){
        ObservableList<ComicAdapter> data = ComicListServices.getDataComic();
        FilteredList<ComicAdapter> searchData = new FilteredList<>(data);

        lblImageComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("image"));
        lblNameComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("name"));
        lblNumberComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("number"));
        lblPagesComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("page"));
        lblPublicationComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("anhoPublication"));

        tableComics.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {searchData.setPredicate(searchComics());});
        SortedList<ComicAdapter> sortedData = new SortedList<>(searchData);
        tableComics.setItems(sortedData);
        sortedData.comparatorProperty().bind(tableComics.comparatorProperty());
    }

    public void doubleClickButton(){
        tableComics.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 2){
                    TablePosition tablePosition = (TablePosition) tableComics.getSelectionModel().getSelectedCells().get(0);
                    int row = tablePosition.getRow();
                    ComicAdapter item = (ComicAdapter) tableComics.getItems().get(row);
                    getPanelDetailsShow(item);
                }
            }
        });
    }

    public void getPanelDetailsShow(ComicAdapter comicAdapter){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicDetails.fxml"), resourceBundle);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.setMinWidth(1130);
            stage.setMinHeight(900);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tableComics.getScene().getWindow());

            ComicDetailsController detailsController = fxmlLoader.<ComicDetailsController>getController();
            detailsController.initData(comicAdapter);

            stage.showAndWait();
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorShowComic"));
            alert.showAndWait();
        }

    }

    private Predicate<ComicAdapter> searchComics (){
        return order -> {
            if (txtSearch.getText() == null || txtSearch.getText().isEmpty()) return true;
            return searchComic(order, txtSearch.getText());
        };
    }

    private static boolean searchComic(ComicAdapter comic, String searchText) {
        return (comic.getName().startsWith(searchText));
    }


}
