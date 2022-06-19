package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.HomeApplication;
import com.cifprodolfo.comic_store.HomeController;
import com.cifprodolfo.comic_store.services.CollectionListServices;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CollectionViewController {

    @FXML
    private StackPane stagePanel;
    @FXML
    private TableView tableCollection;
    @FXML
    private TableColumn<CollectionAdapter, String> lblImageCollection;
    @FXML
    private TableColumn<CollectionAdapter, String> lblNameCollection;
    @FXML
    private TableColumn<CollectionAdapter, String> lblEditorialCollection;
    private TextField txtSearch = new TextField();

    ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");

    public CollectionViewController(TextField text) { this.txtSearch = text; }

    public void initialize(){

        ObservableList<CollectionAdapter> data = CollectionListServices.getDataCollections();
        FilteredList<CollectionAdapter> searchData = new FilteredList<>(data);

        lblImageCollection.setCellValueFactory(new PropertyValueFactory<CollectionAdapter, String>("image"));
        lblNameCollection.setCellValueFactory(new PropertyValueFactory<CollectionAdapter, String>("name"));
        lblEditorialCollection.setCellValueFactory(new PropertyValueFactory<CollectionAdapter, String>("editorial"));

        tableCollection.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
        txtSearch.textProperty().addListener(((observableValue, oldValue, newValue) -> {searchData.setPredicate(searchCollections());}));
        SortedList<CollectionAdapter> sortedData = new SortedList<>(searchData);
        tableCollection.setItems(sortedData);
        sortedData.comparatorProperty().bind(tableCollection.comparatorProperty());
    }

    public void doubleClickButton(){
        tableCollection.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 2){
                    TablePosition tablePosition = (TablePosition) tableCollection.getSelectionModel().getSelectedCells().get(0);
                    int row = tablePosition.getRow();
                    CollectionAdapter item = (CollectionAdapter) tableCollection.getItems().get(row);
                    getPanelDetailsShow(item);
                }
            }
        });
    }

    public void getPanelDetailsShow(CollectionAdapter CollectionAdapter){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("collectionDetails.fxml"), resourceBundle);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.setTitle(resourceBundle.getString("lblDetailsCollections"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tableCollection.getScene().getWindow());
            stage.getIcons().add(new Image(HomeApplication.class.getResourceAsStream("/images/icon_photo.png")));
            stage.setMinHeight(600);
            stage.setMinWidth(800);

            CollectionDetailsController detailsController = fxmlLoader.<CollectionDetailsController>getController();
            detailsController.initData(CollectionAdapter);

            stage.showAndWait();
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorShowCollection"));
            alert.showAndWait();
        }

    }

    private Predicate<CollectionAdapter> searchCollections(){
        return order -> {
          if(txtSearch.getText() == null || txtSearch.getText().isEmpty()) return true;
          return searchCollection(order, txtSearch.getText());
        };
    }

    private static boolean searchCollection(CollectionAdapter collection, String searchText){
        return (collection.getName().startsWith(searchText));
    }
}
