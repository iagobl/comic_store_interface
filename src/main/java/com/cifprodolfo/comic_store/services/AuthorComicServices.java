package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.model.Author;
import com.cifprodolfo.comic_store.model.AuthorComic;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthorComicServices {

    public static void saveAuthorComic(int timeDedicated, Long idAuthor, Long idComic) throws IOException, InterruptedException {

        String url = "http://192.168.224.128:8080/api-spring/authorcomic";

        HttpClient httpClient = HttpClient.newHttpClient();
        String json = "{\n" +
                "        \"timeDedicated\": "+ timeDedicated + ",\n" +
                "        \"comic\": {\n" +
                "            \"id\": "+ idComic + "\n" +
                "        },\n" +
                "        \"author\": {\n" +
                "            \"id\": "+idAuthor+"\n" +
                "        }\n" +
                "}";

        HttpRequest httpRequest = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json)).uri(URI.create(url)).header("Content-Type", "application/json").build();
        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    /*public static AuthorComic getAuthorComicById(Long idComic) throws IOException, InterruptedException {

        AuthorComic authorComic = new AuthorComic();
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://localhost:8080/api-spring/authorcomic/findByIdComic/" + idComic;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpResponse <String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        authorComic = objectMapper.readValue(response.body(), new TypeReference<AuthorComic>() {});
        return authorComic;

    }*/
}
