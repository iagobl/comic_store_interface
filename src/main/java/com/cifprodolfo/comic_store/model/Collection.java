package com.cifprodolfo.comic_store.model;

import java.util.List;

public class Collection {

    private Long id;
    private String name;
    private byte[] image;
    private String editorial;

    public Collection() {}

    public Collection(Long id, String name, byte[] image, String editorial) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.editorial = editorial;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }

    public String getEditorial() { return editorial; }

    public void setEditorial(String editorial) { this.editorial = editorial; }

    @Override
    public String toString() {
        return name + "---" + editorial;
    }
}
