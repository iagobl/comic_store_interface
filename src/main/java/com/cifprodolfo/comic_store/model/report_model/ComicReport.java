package com.cifprodolfo.comic_store.model.report_model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ComicReport {

    private Long id;
    private String name;
    private InputStream image;
    private String synopsis;
    private int number;
    private int page;
    private String tapa;
    private int anhoPublication;
    private String dataAcquisition;
    private String state;
    private double price;
    private String authorName;

    public ComicReport() {}

    public ComicReport(Long id, String name, InputStream image, String synopsis, int number, int page, String tapa, int anhoPublication, String dataAcquisition, String state, double price, String authorName) {
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

    public InputStream getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = new ByteArrayInputStream(image);
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

    public String getDataAcquisition() {
        return dataAcquisition;
    }

    public void setDataAcquisition(LocalDate dataAcquisition) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        this.dataAcquisition = dataAcquisition.format(formatter);
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
}
