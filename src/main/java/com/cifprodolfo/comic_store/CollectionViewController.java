package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.model.Collection;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

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
        data = getDataCollections();

        lblImageCollection.setCellValueFactory(new PropertyValueFactory<CollectionAdapter, String>("image"));
        lblNameCollection.setCellValueFactory(new PropertyValueFactory<CollectionAdapter, String>("name"));
        lblEditorialCollection.setCellValueFactory(new PropertyValueFactory<CollectionAdapter, String>("editorial"));

        tableCollection.setItems(data);
        tableCollection.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public ObservableList<CollectionAdapter> getDataCollections(){
        ObservableList<CollectionAdapter> collectionAdaptersList = FXCollections.observableArrayList();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api-spring/collection")).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            List<Collection> data =  objectMapper.readValue(response.body(), new TypeReference<>() {});
            for(Collection collection: data){
                final byte[] ImageBytes = collection.getImage();
                ImageView photo = new ImageView(new Image(new ByteArrayInputStream(ImageBytes)));
                photo.setFitWidth(60);
                photo.setFitHeight(60);

                collectionAdaptersList.add(
                        new CollectionAdapter(
                                collection.getId(),
                                collection.getName(),
                                photo,
                                collection.getEditorial(),
                                collection.getComicList()));
            }

        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al mostrar los comics");
            alert.showAndWait();
        }

        return collectionAdaptersList;
    }
}
