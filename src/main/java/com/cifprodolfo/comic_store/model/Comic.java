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
    private LocalDate dataAcquisition;
    private String state;
    private double price;
    private String authorName;
    private Long collection_id;
    private Integer timeDedicated;

    public Comic(){}
    public Comic(Long id, String name, byte[] image, String synopsis, int number, int page, String tapa, int anhoPublication, LocalDate dataAcquisition, String state, double price, String authorName, Long collection_id, Integer timeDedicated) {
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
        this.authorName = authorName;
        this.collection_id = collection_id;
        this.timeDedicated = timeDedicated;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTapa() {
        return tapa;
    }

    public void setTapa(String tapa) {
        this.tapa = tapa;
    }

    public int getAnhoPublication() {
        return anhoPublication;
    }

    public void setAnhoPublication(int anhoPublication) {
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getCollection_id(){
        return collection_id;
    }

    public void setCollection_id(Long collection_id){
        this.collection_id = collection_id;
    }

    public Integer getTimeDedicated() { return timeDedicated; }

    public void setTimeDedicated(Integer timeDedicated) { this.timeDedicated = timeDedicated; }
}
