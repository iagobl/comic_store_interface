package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.HomeController;
import com.cifprodolfo.comic_store.services.ComicListServices;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
    static ObservableList<ComicAdapter> data = FXCollections.observableArrayList();
    static FilteredList<ComicAdapter> searchData = new FilteredList<>(FXCollections.observableList(data));

    public ComicViewController(){

    }

    private static Predicate<ComicAdapter> searchComics (String searchText){
        return order -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchComic(order, searchText);
        };
    }

    private static boolean searchComic(ComicAdapter comic, String searchText) {
        return (comic.getName().startsWith(searchText));
    }

    public void initialize(){

        data = ComicListServices.getDataComic();

        lblImageComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("image"));
        lblNameComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("name"));
        lblNumberComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("number"));
        lblPagesComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("page"));
        lblPublicationComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("anhoPublication"));

        tableComics.setItems(data);
        tableComics.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        /*txtSearch = (TextField) stackPaneComics.getParent().getScene().lookup("#txtSearch");
        txtSearch.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldProperty, Boolean newProperty) {
                if(newProperty){
                    searchData.setPredicate(searchComics(txtSearch.getText()));
                }
            }
        });*/
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
            ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
            FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("comicDetails2.fxml"), resourceBundle);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tableComics.getScene().getWindow());

            ComicDetailsController detailsController = fxmlLoader.<ComicDetailsController>getController();
            detailsController.initData(comicAdapter);

            stage.showAndWait();
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al mostrar el comic");
            alert.showAndWait();
        }

    }


}
