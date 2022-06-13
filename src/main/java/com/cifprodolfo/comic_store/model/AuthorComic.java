package com.cifprodolfo.comic_store.model;

public class AuthorComic {

    private Long id;
    private Comic comic;
    private Author author;
    private String job;

    public AuthorComic(){}

    public AuthorComic(Long id, Comic comic, Author author, String job){
        this.id = id;
        this.comic = comic;
        this.author = author;
        this.job = job;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Comic getComic() { return comic; }

    public void setComic(Comic comic) { this.comic = comic; }

    public Author getAuthor() { return author; }

    public void setAuthor(Author author) { this.author = author; }

    public String getJob() { return job; }

    public void setJob(String job) { this.job = job; }
}
