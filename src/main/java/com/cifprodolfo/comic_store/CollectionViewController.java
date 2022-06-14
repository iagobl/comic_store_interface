package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.services.GetCollectionList;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.util.ResourceBundle;

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

    public CollectionViewController() {}

    public void initialize(){

        ObservableList<CollectionAdapter> data = FXCollections.observableArrayList();
        data = GetCollectionList.getDataCollections();

        lblImageCollection.setCellValueFactory(new PropertyValueFactory<CollectionAdapter, String>("image"));
        lblNameCollection.setCellValueFactory(new PropertyValueFactory<CollectionAdapter, String>("name"));
        lblEditorialCollection.setCellValueFactory(new PropertyValueFactory<CollectionAdapter, String>("editorial"));

        tableCollection.setItems(data);
        tableCollection.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
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
            ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
            FXMLLoader fxmlLoader = new FXMLLoader(CollectionViewController.class.getResource("collectionDetails.fxml"), resourceBundle);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.setTitle("Colecciones");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tableCollection.getScene().getWindow());

            CollectionDetailsController detailsController = fxmlLoader.<CollectionDetailsController>getController();
            detailsController.initData(CollectionAdapter);

            stage.showAndWait();
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hubo un error al mostrar el comic");
            alert.showAndWait();
        }

    }
}
