package com.cifprodolfo.comic_store.model;

import java.util.List;

public class Author {

    private Long id;
    private String name;
    private String surname;
    private List<AuthorComic> authorComicList;

    public Author() {}

    public Author(Long id, String name, String surname, List<AuthorComic> authorComicList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.authorComicList = authorComicList;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname;}

    public List<AuthorComic> getAuthorComicList() { return authorComicList; }

    public void setAuthorComicList(List<AuthorComic> authorComicList) { this.authorComicList = authorComicList; }
}
