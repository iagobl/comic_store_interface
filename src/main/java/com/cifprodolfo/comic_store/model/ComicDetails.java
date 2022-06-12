package com.cifprodolfo.comic_store.model;

import java.time.LocalDate;

public class ComicDetails {

    private Long id;
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
