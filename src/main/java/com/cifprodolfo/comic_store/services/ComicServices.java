package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.model.Comic;
import com.cifprodolfo.comic_store.model.adapter.NewCollectionAdapater;
import com.cifprodolfo.comic_store.model.adapter.NewComicAdapter;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;

public class ComicServices {

    public static NewComicAdapter saveComics(String name, String synopsis, String number, String page, String tape, LocalDate date, String anhoPublication, String state, String price, Long idCollection, String timeDedicated, Long idAuthor) throws IOException, InterruptedException {

        NewComicAdapter comicNew;
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://192.168.224.128:8080/api-spring/comic/" + idAuthor;

        HttpClient httpClient = HttpClient.newHttpClient();
        String json = new StringBuffer()
                .append("{")
                .append("\"name\":\"" + name + "\",")
                .append("\"synopsis\":\"" + synopsis + "\",")
                .append("\"number\":\"" + number + "\",")
                .append("\"page\":\"" + page + "\",")
                .append("\"tapa\":\"" + tape + "\",")
                .append("\"anhoPublication\":\"" + anhoPublication + "\",")
                .append("\"dataAcquisition\":\"" + date + "\",")
                .append("\"state\":\"" + state + "\",")
                .append("\"price\":\"" + price + "\",")
                .append("\"authorComic\":" + "{\"timeDedicated\":" + timeDedicated + "}" + ",")
                .append("\"collection\":" + "{\"id\":" + idCollection + "}" + "")
                .append("}")
                .toString();

        HttpRequest httpRequest = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json)).uri(URI.create(url)).header("Content-Type", "application/json").build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 302){
            return new NewComicAdapter();
        }
        comicNew = objectMapper.readValue(response.body(), new TypeReference<NewComicAdapter>() {});
        return comicNew;
    }

    public static NewComicAdapter putComics(ComicAdapter comicAdapter) throws IOException, InterruptedException {

        NewComicAdapter newComicAdapter;
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://192.168.224.128:8080/api-spring/comic";
        String json = "{\n" +
                "    \"id\": " + comicAdapter.getId() + ",\n" +
                "    \"name\": \"" + comicAdapter.getName() + "\",\n" +
                "    \"synopsis\": \"" + comicAdapter.getSynopsis() + "\",\n" +
                "    \"number\": " + comicAdapter.getNumber() + ",\n" +
                "    \"page\": " + comicAdapter.getPage() + ",\n" +
                "    \"tapa\": \"" + comicAdapter.getTapa() + "\",\n" +
                "    \"dataAcquisition\": \"" + comicAdapter.getDateAcquistion() + "\",\n" +
                "    \"anhoPublication\": " + comicAdapter.getAnhoPublication() + ",\n" +
                "    \"state\": \"" + comicAdapter.getState() + "\",\n" +
                "    \"price\": " + comicAdapter.getPrice() + ",\n" +
                "    \"authorComic\": {\n" +
                "        \"timeDedicated\" : " + comicAdapter.getTimeDedicated() + "\n" +
                "    }\n" +
                "}";

        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(5)).build();
        HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").uri(URI.create(url)).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 302) {
            return new NewComicAdapter();
        }

        newComicAdapter = objectMapper.readValue(response.body(), new TypeReference<NewComicAdapter>() {});
        return newComicAdapter;
    }

    public static void uploadImage(Long id, String pathImage) throws IOException, InterruptedException, URISyntaxException {

        String url = "http://192.168.224.128:8080/api-spring/comic/image/" + id;
        HttpEntity httpEntity = MultipartEntityBuilder.create().addBinaryBody("imageComic", new File(pathImage), ContentType.IMAGE_PNG, "unknown.png").build();
        Pipe pipe = Pipe.open();

        new Thread(() -> {
            try (OutputStream outputStream = Channels.newOutputStream(pipe.sink())){
                httpEntity.writeTo(outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(new URI(url)).header("Content-Type", httpEntity.getContentType().getValue()).PUT(HttpRequest.BodyPublishers.ofInputStream(() -> Channels.newInputStream(pipe.source()))).build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
    }

    public static void deleteComic(ComicAdapter comicAdapter) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        String deleteCollection = "http://192.168.224.128:8080/api-spring/comic/"+comicAdapter.getId();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(deleteCollection)).DELETE().build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
        ComicListServices.updateDataComic();

    }

}
