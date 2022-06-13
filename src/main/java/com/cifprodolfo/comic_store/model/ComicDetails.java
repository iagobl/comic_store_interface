package com.cifprodolfo.comic_store.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class ComicDetails {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateAcquistion;
    private String state;
    private double price;

    public ComicDetails(){}

    public ComicDetails(Long id, LocalDate dateAcquistion, String state, double price){
        this.id = id;
        this.dateAcquistion = dateAcquistion;
        this.state = state;
        this.price = price;
    }

    public Long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public LocalDate getDateAcquistion() { return dateAcquistion; }

    public void setDateAcquistion(LocalDate dateAcquistion) { this.dateAcquistion = dateAcquistion; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }


}
