package com.cifprodolfo.comic_store.model.report_model;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class CollectionReport {

    private Long id;
    private String name;
    private InputStream image;
    private String editorial;

    public CollectionReport() {}

    public CollectionReport(Long id, String name, InputStream image, String editorial) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.editorial = editorial;
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

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
