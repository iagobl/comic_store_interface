package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CollectionViewController {

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
}
