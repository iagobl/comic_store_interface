package com.cifprodolfo.comic_store.model;

import java.util.List;

public class Comic {

    private Long id;
    private String name;
    private byte[] image;
    private String synopsis;
    private int number;
    private int page;
    private int anhoPublication;
    private List<AuthorComic> authorComic;
    private List<ComicDetails> comicDetails;

    public Comic(){}

    public Comic(Long id, String name, byte[] image, String synopsis, int number, int page, int anhoPublication, List<AuthorComic> authorComic, List<ComicDetails> comicDetails){
        this.id = id;
        this.name = name;
        this.image = image;
        this.synopsis = synopsis;
        this.number = number;
        this.page = page;
        this.anhoPublication = anhoPublication;
        this.authorComic = authorComic;
        this.comicDetails = comicDetails;
    }

    public Long getId() { return id; }

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

    public void setImage(byte[] image) { this.image = image; }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPage() { return page; }

    public void setPage(int page) { this.page = page; }

    public int getAnhoPublication() {return anhoPublication; }

    public void setAnhoPublication(int anhoPublication) { this.anhoPublication = anhoPublication; }

    public List<AuthorComic> getAuthorComic() { return authorComic; }

    public void setAuthorComic(List<AuthorComic> authorComic) { this.authorComic = authorComic; }

    public List<ComicDetails> getComicDetails() { return comicDetails; }

    public void setComicDetail(List<ComicDetails> comicDetails) { this.comicDetails = comicDetails; }
}
