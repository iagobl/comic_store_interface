package com.cifprodolfo.comic_store.model.adapter;

import java.util.List;

public class NewCollectionAdapater {

    private Long id;
    private String name;
    private byte[] image;
    private String editorial;
    private List<NewComicAdapter> comicList;

    public NewCollectionAdapater(){}

    public NewCollectionAdapater(Long id, String name, byte[] image, String editorial, List<NewComicAdapter> comicList) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.editorial = editorial;
        this.comicList = comicList;
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

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public List<NewComicAdapter> getComicList() {
        return comicList;
    }

    public void setComicList(List<NewComicAdapter> comicList) {
        this.comicList = comicList;
    }
}
