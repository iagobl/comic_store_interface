package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.model.Author;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;


public class AuthorViewController {

    @FXML
    private TableView tableAuthor;
    @FXML
    private TableColumn<AuthorAdapter, String> lblImageAuthor;
    @FXML
    private TableColumn<AuthorAdapter, String> lblNameAuthor;
    @FXML
    private TableColumn<AuthorAdapter, String> lblSurnameAuthor;

    public AuthorViewController() {}

    public void initialize(){

        ObservableList<AuthorAdapter> data = FXCollections.observableArrayList();
        data = getDataAuthors();

        lblImageAuthor.setCellValueFactory(new PropertyValueFactory<AuthorAdapter, String>("image"));
        lblNameAuthor.setCellValueFactory(new PropertyValueFactory<AuthorAdapter, String>("name"));
        lblSurnameAuthor.setCellValueFactory(new PropertyValueFactory<AuthorAdapter, String>("surname"));

        tableAuthor.setItems(data);
        tableAuthor.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public ObservableList<AuthorAdapter> getDataAuthors() {
        ObservableList<AuthorAdapter> authorAdaptersList = FXCollections.observableArrayList();
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

}
