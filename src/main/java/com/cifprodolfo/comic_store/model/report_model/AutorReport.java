package com.cifprodolfo.comic_store.model.report_model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AutorReport {

    private Long id;
    private String name;
    private InputStream image;
    private String surname;

    public AutorReport(){}

    public AutorReport(Long id, String name, InputStream image, String surname) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.surname = surname;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public InputStream getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = new ByteArrayInputStream(image);
    }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname;}
}
