package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.model.Collection;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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

public class GetCollectionList {

    static ObservableList <CollectionAdapter> collectionAdaptersList = FXCollections.observableArrayList();
    public static ObservableList<CollectionAdapter> getDataCollections(){

        collectionAdaptersList.clear();
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

    public static ObservableList<CollectionAdapter> updateDataCollections(){

        collectionAdaptersList.clear();
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
