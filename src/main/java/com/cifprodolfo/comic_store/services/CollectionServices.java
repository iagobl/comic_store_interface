package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.model.Collection;
import com.cifprodolfo.comic_store.model.adapter.NewCollectionAdapater;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
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

public class CollectionServices {

    public static NewCollectionAdapater saveCollections(String nameCollection, String editorialCollection) throws IOException, InterruptedException {

        NewCollectionAdapater newCollectionAdapater;
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://192.168.224.128:8080/api-spring/collection";

        HttpClient httpClient = HttpClient.newHttpClient();
        String json = new StringBuffer()
                .append("{")
                .append("\"name\":\"" + nameCollection + "\",")
                .append("\"editorial\":\"" + editorialCollection + "\",")
                .append("\"comicList\":" + "["+"]" + "")
                .append("}")
                .toString();
        HttpRequest httpRequest = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json)).uri(URI.create(url)).header("Content-Type", "application/json").build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 302) {
            return new NewCollectionAdapater();
        }

        newCollectionAdapater = objectMapper.readValue(response.body(), new TypeReference<>() {});
        return newCollectionAdapater;
    }

    public static NewCollectionAdapater putCollections(CollectionAdapter collectionAdapter) throws IOException, InterruptedException{

        NewCollectionAdapater newCollectionAdapater;
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://192.168.224.128:8080/api-spring/collection";
        String json = "{\n" +
                "    \"id\": "+collectionAdapter.getId()+",\n" +
                "    \"name\": \""+collectionAdapter.getName()+"\",\n" +
                "    \"editorial\": \""+collectionAdapter.getEditorial()+"\",\n" +
                "    \"comicList\": []\n" +
                "}";

        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(5)).build();
        HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").uri(URI.create(url)).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 302 ){
            return new NewCollectionAdapater();
        }

        newCollectionAdapater = objectMapper.readValue(response.body(), new TypeReference<>() {});
        return newCollectionAdapater;
    }

    public static void uploadImage(CollectionAdapter collection, String pathImage) throws IOException, URISyntaxException, InterruptedException {

        String url = "http://192.168.224.128:8080/api-spring/collection/image/"+collection.getId();
        HttpEntity httpEntity = MultipartEntityBuilder.create().addBinaryBody("imageCollection", new File(pathImage), ContentType.IMAGE_PNG, "unknown.png").build();
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

    public static void deleteCollection(CollectionAdapter collection) throws IOException, InterruptedException{

        HttpClient client = HttpClient.newHttpClient();
        String deleteCollection = "http://192.168.224.128:8080/api-spring/collection/"+collection.getId();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(deleteCollection)).DELETE().build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
        CollectionListServices.updateDataCollections();

    }

}
