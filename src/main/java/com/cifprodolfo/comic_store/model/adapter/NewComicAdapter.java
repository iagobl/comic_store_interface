package com.cifprodolfo.comic_store.model.adapter;


import com.cifprodolfo.comic_store.model.AuthorComic;
import com.cifprodolfo.comic_store.model.Collection;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class NewComicAdapter {

    private Long id;
    private String name;
    private byte[] image;
    private String synopsis;
    private Integer number;
    private Integer page;
    private String tapa;
    private Integer anhoPublication;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataAcquisition;
    private String state;
    private double price;
    private AuthorComic authorComic;
    private NewCollectionAdapater collection;



    public NewComicAdapter() {}

    public NewComicAdapter(Long id, String name, byte[] image, String synopsis, Integer number, Integer page, String tapa, Integer anhoPublication, LocalDate dataAcquisition, String state, double price, AuthorComic authorComic, NewCollectionAdapater collection) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.synopsis = synopsis;
        this.number = number;
        this.page = page;
        this.tapa = tapa;
        this.anhoPublication = anhoPublication;
        this.dataAcquisition = dataAcquisition;
        this.state = state;
        this.price = price;
        this.authorComic = authorComic;
        this.collection = collection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getTapa() {
        return tapa;
    }

    public void setTapa(String tapa) {
        this.tapa = tapa;
    }

    public Integer getAnhoPublication() {
        return anhoPublication;
    }

    public void setAnhoPublication(Integer anhoPublication) {
        this.anhoPublication = anhoPublication;
    }

    public LocalDate getDataAcquisition() {
        return dataAcquisition;
    }

    public void setDataAcquisition(LocalDate dataAcquisition) {
        this.dataAcquisition = dataAcquisition;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AuthorComic getAuthorComic() {
        return authorComic;
    }

    public void setAuthorComic(AuthorComic authorComic) {
        this.authorComic = authorComic;
    }

    public NewCollectionAdapater getCollection() {
        return collection;
    }

    public void setCollection(NewCollectionAdapater collection) {
        this.collection = collection;
    }
}
