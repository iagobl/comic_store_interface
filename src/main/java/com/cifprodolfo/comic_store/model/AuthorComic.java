package com.cifprodolfo.comic_store.model;

public class AuthorComic {

    private Long id;
    private Comic comic;
    private Author author;
    private int timeDedicated;

    public AuthorComic(){}

    public AuthorComic(Long id, Comic comic, Author author, int timeDedicated){
        this.id = id;
        this.comic = comic;
        this.author = author;
        this.timeDedicated = timeDedicated;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Comic getComic() { return comic; }

    public void setComic(Comic comic) { this.comic = comic; }

    public Author getAuthor() { return author; }

    public void setAuthor(Author author) { this.author = author; }

    public int gettimeDedicated() { return timeDedicated; }

    public void settimeDedicated(String job) { this.timeDedicated = timeDedicated; }
}
