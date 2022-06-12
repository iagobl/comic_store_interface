package com.cifprodolfo.comic_store.model;

public class Comic {

    private Long id;
    private String name;
    private String image;
    private String synopsis;
    private int number;
    private int page;
    private int anhoPublication;

    public Comic(){}

    public Comic(Long id, String name, String image, String synopsis, int number, int page, int anhoPublication){
        this.id = id;
        this.name = name;
        this.image = image;
        this.synopsis = synopsis;
        this.number = number;
        this.page = page;
        this.anhoPublication = anhoPublication;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) { this.image = image; }

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
}
