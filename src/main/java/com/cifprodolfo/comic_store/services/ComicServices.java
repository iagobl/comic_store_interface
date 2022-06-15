package com.cifprodolfo.comic_store.services;

import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ComicServices {

    public static void deleteComic(ComicAdapter comicAdapter) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        String deleteCollection = "http://localhost:8080/api-spring/comic/"+comicAdapter.getId();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(deleteCollection)).DELETE().build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
        GetComicList.updateDataComic();

    }
}
