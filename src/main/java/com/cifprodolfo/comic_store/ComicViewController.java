package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.model.Comic;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
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

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;


public class ComicViewController {

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

    public ComicViewController(){

    }

    public void initialize(){

        getDataComic();
        ImageView photoExample = new ImageView(new Image(this.getClass().getResourceAsStream("/images/icon_photo.png")));
        photoExample.setFitWidth(60);
        photoExample.setFitHeight(60);
        ObservableList<ComicAdapter> data = FXCollections.observableArrayList(
                new ComicAdapter(1321312L, "iago", photoExample, "lkjlfdf", 2, 322, 123)
        );

        lblImageComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("image"));
        /*lblImageComic.setCellFactory(new Callback<TableColumn<ComicAdapter, String>, TableCell<ComicAdapter, String>>() {
            @Override
            public TableCell<ComicAdapter, String> call(TableColumn<ComicAdapter, String> comicAdapterStringTableColumn) {
                TableCell<ComicAdapter, String> tc = new TableCell<>();
                tc.setAlignment(Pos.CENTER);
                return tc;
            }
        });*/

        lblNameComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("name"));
        lblNumberComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("number"));
        lblPagesComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("page"));
        lblPublicationComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("number"));

        tableComics.setItems(data);
        tableComics.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void getDataComic(){
        try {

            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api-spring/comic")).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            ObservableList<Comic> data = FXCollections.observableArrayList();
            data = (ObservableList<Comic>) objectMapper.readValue(response.body(), new TypeReference<List<Comic>>() {});
            System.out.println(data.size());
            ;
        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al mostrar los comics");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
