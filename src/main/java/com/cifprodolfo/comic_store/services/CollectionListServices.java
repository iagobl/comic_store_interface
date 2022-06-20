package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.model.Collection;
import com.cifprodolfo.comic_store.model.report_model.CollectionReport;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CollectionListServices {

    static ObservableList <CollectionAdapter> collectionAdaptersList = FXCollections.observableArrayList();
    static ObservableList <Collection> collectionList = FXCollections.observableArrayList();
    static List<Collection> collectionListReport = FXCollections.observableArrayList();
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
    public static ObservableList<CollectionAdapter> getDataCollections(){

        collectionAdaptersList.clear();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://192.168.224.128:8080/api-spring/collection")).build();
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
                                collection.getEditorial()));
            }

        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("txtErrorShowCollection"));
            alert.showAndWait();
        }

        return collectionAdaptersList;
    }

    public static List<CollectionReport> collectionList(){

        collectionListReport.clear();
        ObjectMapper objectMapper = new ObjectMapper();
        List<CollectionReport> listCollection = new ArrayList<>();

        try {
            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://192.168.224.128:8080/api-spring/collection")).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            collectionListReport =  objectMapper.readValue(response.body(), new TypeReference<>() {});
            for(Collection collection: collectionListReport) {
                CollectionReport collectionReport = new CollectionReport();
                collectionReport.setId(collection.getId());
                collectionReport.setName(collection.getName());
                collectionReport.setEditorial(collection.getEditorial());
                collectionReport.setImage(collection.getImage());

                listCollection.add(collectionReport);
            }


        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("txtErrorShowCollection"));
            alert.showAndWait();
        }

        return listCollection;
    }

    public static FilteredList<CollectionAdapter> updateDataCollections(){

        collectionAdaptersList.clear();
        ObjectMapper objectMapper = new ObjectMapper();
        FilteredList<CollectionAdapter> searchData = new FilteredList<>(FXCollections.observableList(collectionAdaptersList));

        try {

            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://192.168.224.128:8080/api-spring/collection")).build();
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
                                collection.getEditorial()));
            }

        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("txtErrorShowCollection"));
            alert.showAndWait();
        }

        return searchData;
    }

    public static ObservableList <Collection> getCollectionfromCombo() throws IOException, InterruptedException {

        collectionList.clear();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(5)).build();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://192.168.224.128:8080/api-spring/collection")).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        List<Collection> data =  objectMapper.readValue(response.body(), new TypeReference<>() {});
        for(Collection collection: data){
            collectionList.add(collection);
        }

        return collectionList;
    }

    public static Collection getCollectionById(Long id) throws IOException, InterruptedException {

        Collection collection;
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://192.168.224.128:8080/api-spring/collection/" + id;

        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(5)).build();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        collection = objectMapper.readValue(response.body(), new TypeReference<>() {});
        return collection;
    }
}
