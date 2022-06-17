package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.model.Author;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
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
import java.util.List;
import java.util.logging.Filter;

public class AuthorListServices {

    static ObservableList<AuthorAdapter> authorAdaptersList = FXCollections.observableArrayList();
    static ObservableList<Author> authorList = FXCollections.observableArrayList();

    public static ObservableList<AuthorAdapter> getDataAuthors() {

        authorAdaptersList.clear();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api-spring/author")).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            List<Author> data = objectMapper.readValue(response.body(), new TypeReference<>() {});
            for(Author author : data){
                final byte[] ImageBytes = author.getImage();
                ImageView photo = new ImageView(new Image(new ByteArrayInputStream(ImageBytes)));
                photo.setFitWidth(60);
                photo.setFitHeight(60);

                authorAdaptersList.add(
                        new AuthorAdapter(
                                author.getId(),
                                author.getName(),
                                photo,
                                author.getSurname(),
                                author.getAuthorComicList()
                        )
                );
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al mostrar los autores");
            alert.showAndWait();
        }

        return authorAdaptersList;
    }

    public static FilteredList<AuthorAdapter> updateDataAuthor() {

        authorAdaptersList.clear();
        ObjectMapper objectMapper = new ObjectMapper();
        FilteredList<AuthorAdapter> searchData = new FilteredList<>(FXCollections.observableList(authorAdaptersList));

        try {

            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api-spring/author")).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            List<Author> data = objectMapper.readValue(response.body(), new TypeReference<>() {});
            for(Author author : data){
                final byte[] ImageBytes = author.getImage();
                ImageView photo = new ImageView(new Image(new ByteArrayInputStream(ImageBytes)));
                photo.setFitWidth(60);
                photo.setFitHeight(60);

                authorAdaptersList.add(
                        new AuthorAdapter(
                                author.getId(),
                                author.getName(),
                                photo,
                                author.getSurname(),
                                author.getAuthorComicList()
                        )
                );
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al mostrar los autores");
            alert.showAndWait();
        }

        return searchData;
    }

    public static ObservableList<Author> getAuthorfromCombo() throws IOException, InterruptedException {

        authorList.clear();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api-spring/author")).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        List<Author> data = objectMapper.readValue(response.body(), new TypeReference<>() {});
        for(Author author : data){
            authorList.add(author);
        }

        return authorList;
    }
}
