package com.cifprodolfo.comic_store.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;

public class Comic {

    private Long id;
    private String name;
    private byte[] image;
    private String synopsis;
    private int number;
    private int page;
    private String tapa;
    private int anhoPublication;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateAcquistion;
    private String state;
    private double price;
    private List<AuthorComic> authorComic;

    public Comic(){}

    public Comic(Long id, String name, byte[] image, String synopsis, int number, int page, String tapa, int anhoPublication, LocalDate dateAcquistion, String state, double price, List<AuthorComic> authorComic){
        this.id = id;
        this.name = name;
        this.image = image;
        this.synopsis = synopsis;
        this.number = number;
        this.page = page;
        this.tapa = tapa;
        this.anhoPublication = anhoPublication;
        this.dateAcquistion = dateAcquistion;
        this.state = state;
        this.price = price;
        this.authorComic = authorComic;
    }

    public Long getId() { return id; }

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

    public void setImage(byte[] image) { this.image = image; }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPage() { return page; }

    public void setPage(int page) { this.page = page; }

    public String getTapa() { return tapa; }

    public void setTapa(String tapa) { this.tapa = tapa; }

    public int getAnhoPublication() {return anhoPublication; }

    public void setAnhoPublication(int anhoPublication) { this.anhoPublication = anhoPublication; }

    public LocalDate getDateAcquistion() { return dateAcquistion; }

    public void setDateAcquistion(LocalDate dateAcquistion) { this.dateAcquistion = dateAcquistion; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public List<AuthorComic> getAuthorComic() { return authorComic; }

    public void setAuthorComic(List<AuthorComic> authorComic) { this.authorComic = authorComic; }
}
