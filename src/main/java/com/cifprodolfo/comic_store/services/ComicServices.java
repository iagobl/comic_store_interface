package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.model.Comic;
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

public class ComicServices {

    public static Comic saveComics(String name, String synopsis, String number, String page, String tape, String date, String anhoPublication, String state, String price) throws IOException, InterruptedException {

        Comic comicNew;
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://localhost:8080/api-spring/comic";

        HttpClient httpClient = HttpClient.newHttpClient();
        String json = new StringBuffer()
                .append("{")
                .append("\"name\":\"" + name + "\",")
                .append("\"synopsis\":\"" + synopsis + "\",")
                .append("\"number\":\"" + number + "\",")
                .append("\"page\":\"" + page + "\",")
                .append("\"tapa\":\"" + tape + "\",")
                .append("\"anhoPublication\":\"" + anhoPublication + "\",")
                .append("\"dateAcquistion\":\"" + date + "\",")
                .append("\"state\":\"" + state + "\",")
                .append("\"price\":\"" + price + "\",")
                .append("\"authorComic\":" + "["+"]" + "")
                .append("}")
                .toString();

        HttpRequest httpRequest = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json)).uri(URI.create(url)).header("Content-Type", "application/json").build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        comicNew = objectMapper.readValue(response.body(), new TypeReference<Comic>() {});
        return comicNew;
    }

    public static void uploadImage(ComicAdapter comic, String pathImage) throws IOException, InterruptedException, URISyntaxException {

        String url = "http://localhost:8080/api-spring/comic/image/" + comic.getId();
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
        String deleteCollection = "http://localhost:8080/api-spring/comic/"+comicAdapter.getId();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(deleteCollection)).DELETE().build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
        ComicListServices.updateDataComic();

    }
}
