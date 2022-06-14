package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.model.Author;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class AuthorServices {

    public static Author saveAuthors(String nameAuthor, String surnameAuthor) throws IOException, InterruptedException {

        Author author;
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://localhost:8080/api-spring/author";

        HttpClient httpClient = HttpClient.newHttpClient();
        String json = new StringBuffer()
                .append("{")
                .append("\"name\":\"" + nameAuthor + "\",")
                .append("\"surname\":\"" + surnameAuthor + "\",")
                .append("\"authorComicList\":" + "["+"]" + "")
                .append("}")
                .toString();

        HttpRequest httpRequest = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json)).uri(URI.create(url)).header("Content-Type", "application/json").build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        author = objectMapper.readValue(response.body(), new TypeReference<Author>() {});
        return author;
    }

    public static void uploadImage(AuthorAdapter authorAdapter, String pathImage) throws IOException, URISyntaxException, InterruptedException {

        String url = "http://localhost:8080/api-spring/author/image/" + authorAdapter.getId();
        HttpEntity httpEntity = MultipartEntityBuilder.create().addBinaryBody("imageAuthor", new File(pathImage), ContentType.IMAGE_PNG, "unknown.png").build();
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

    public static void putAuthors(AuthorAdapter authorAdapter) throws IOException, InterruptedException {

        String url = "http://localhost:8080/api-spring/author/"+authorAdapter.getId()+"?name="+authorAdapter.getName()+"&surname="+authorAdapter.getSurname();
        String json = new StringBuilder()
                .append("{")
                .append("\"nombre\":\"prueba\",")
                .append("}")
                .toString();
        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(5)).build();
        HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(json)).uri(URI.create(url)).build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void deleteAuthor(AuthorAdapter author) throws IOException, InterruptedException{

        HttpClient client = HttpClient.newHttpClient();
        String deleteCollection = "http://localhost:8080/api-spring/author/"+author.getId();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(deleteCollection)).DELETE().build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
        GetAuthorList.updateDataAuthor();
    }

}
