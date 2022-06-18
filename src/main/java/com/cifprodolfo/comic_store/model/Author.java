package com.cifprodolfo.comic_store.model;

import java.util.Arrays;
import java.util.List;

public class Author {

    private Long id;
    private String name;
    private byte[] image;
    private String surname;
    private AuthorComic authorComic;

    public Author() {}

    public Author(Long id, String name, byte[] image, String surname, AuthorComic authorComic) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.surname = surname;
        this.authorComic = authorComic;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image)  { this.image = image; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname;}

    public AuthorComic getAuthorComicList() { return authorComic; }

    public void setAuthorComic(AuthorComic authorComicList) { this.authorComic = authorComic; }

    @Override
    public String toString() {
        return name + "---" + surname;
    }
}
