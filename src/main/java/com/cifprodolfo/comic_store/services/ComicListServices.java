package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.model.Comic;
import com.cifprodolfo.comic_store.model.report_model.ComicReport;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
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

public class ComicListServices {

    static ObservableList<ComicAdapter> comicAdaptersList = FXCollections.observableArrayList();
    static List<Comic> comicListReport = FXCollections.observableArrayList();
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");

    public static ObservableList<ComicAdapter> getDataComic(){

        comicAdaptersList.clear();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://192.168.224.128:8080/api-spring/comic")).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            List<Comic> data =  objectMapper.readValue(response.body(), new TypeReference<>() {});
            for(Comic comic: data){
                final byte[] ImageBytes = comic.getImage();
                ImageView photo = new ImageView(new Image(new ByteArrayInputStream(ImageBytes)));
                photo.setFitWidth(60);
                photo.setFitHeight(60);

                comicAdaptersList.add(
                        new ComicAdapter(
                                comic.getId(),
                                comic.getName(),
                                photo,
                                comic.getSynopsis(),
                                comic.getNumber(),
                                comic.getPage(),
                                comic.getTapa(),
                                comic.getAnhoPublication(),
                                comic.getDataAcquisition(),
                                comic.getState(),
                                comic.getPrice(),
                                comic.getAuthorName(),
                                comic.getCollection_id(),
                                comic.getTimeDedicated())
                );
            }

        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("txtErrorShowComic"));
            alert.showAndWait();
        }

        return comicAdaptersList;
    }

    public static List<ComicReport> comicReportListName(String name) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<ComicReport> listComic = new ArrayList<>();
        String newName = name.replaceAll(" ", "+");

        try {
            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://192.168.224.128:8080/api-spring/comic/findComicReport/"+newName)).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Comic comic =  objectMapper.readValue(response.body(), new TypeReference<>() {});

            ComicReport comicReport = new ComicReport();
            comicReport.setId(comic.getId());
            comicReport.setName(comic.getName());
            comicReport.setImage(comic.getImage());
            comicReport.setSynopsis(comic.getSynopsis());
            comicReport.setNumber(comic.getNumber());
            comicReport.setPage(comic.getPage());
            comicReport.setTapa(comic.getTapa());
            comicReport.setAnhoPublication(comic.getAnhoPublication());
            comicReport.setDataAcquisition(comic.getDataAcquisition());
            comicReport.setState(comic.getState());
            comicReport.setPrice(comic.getPrice());
            comicReport.setAuthorName(comic.getAuthorName());

            listComic.add(comicReport);


        } catch (Exception e) {
        }

        return listComic;
    }

    public static List<ComicReport> comicList(){

        comicListReport.clear();
        ObjectMapper objectMapper = new ObjectMapper();
        List<ComicReport> listComic = new ArrayList<>();

        try {
            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://192.168.224.128:8080/api-spring/comic")).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            comicListReport =  objectMapper.readValue(response.body(), new TypeReference<>() {});
            for(Comic comic: comicListReport){
                ComicReport comicReport = new ComicReport();
                comicReport.setId(comic.getId());
                comicReport.setName(comic.getName());
                comicReport.setImage(comic.getImage());
                comicReport.setSynopsis(comic.getSynopsis());
                comicReport.setNumber(comic.getNumber());
                comicReport.setPage(comic.getPage());
                comicReport.setTapa(comic.getTapa());
                comicReport.setAnhoPublication(comic.getAnhoPublication());
                comicReport.setDataAcquisition(comic.getDataAcquisition());
                comicReport.setState(comic.getState());
                comicReport.setPrice(comic.getPrice());
                comicReport.setAuthorName(comic.getAuthorName());

                listComic.add(comicReport);

            }
        } catch (IOException | InterruptedException e) {

        }

        return listComic;
    }

    public static FilteredList<ComicAdapter> updateDataComic(){

        comicAdaptersList.clear();
        ObjectMapper objectMapper = new ObjectMapper();
        FilteredList<ComicAdapter> searchData = new FilteredList<>(FXCollections.observableList(comicAdaptersList));

        try {

            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://192.168.224.128:8080/api-spring/comic")).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            List<Comic> data =  objectMapper.readValue(response.body(), new TypeReference<>() {});
            for(Comic comic: data){
                final byte[] ImageBytes = comic.getImage();
                ImageView photo = new ImageView(new Image(new ByteArrayInputStream(ImageBytes)));
                photo.setFitWidth(60);
                photo.setFitHeight(60);

                comicAdaptersList.add(
                        new ComicAdapter(
                                comic.getId(),
                                comic.getName(),
                                photo,
                                comic.getSynopsis(),
                                comic.getNumber(),
                                comic.getPage(),
                                comic.getTapa(),
                                comic.getAnhoPublication(),
                                comic.getDataAcquisition(),
                                comic.getState(),
                                comic.getPrice(),
                                comic.getAuthorName(),
                                comic.getCollection_id(),
                                comic.getTimeDedicated())
                );
            }

        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("txtErrorShowComic"));
            alert.showAndWait();
        }

        return searchData;
    }

}
