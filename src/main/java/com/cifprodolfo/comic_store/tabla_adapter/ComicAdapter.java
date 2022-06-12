package com.cifprodolfo.comic_store.tabla_adapter;

import javafx.beans.property.*;
import javafx.scene.image.ImageView;

public class ComicAdapter {

    private final LongProperty id;
    private final StringProperty name;
    private ImageView image;
    private final StringProperty synopsis;
    private final IntegerProperty number;
    private final IntegerProperty page;
    private final IntegerProperty anhoPublication;

    public ComicAdapter(){
        this(null, null, null, null, 0, 0, 0);
    }

    public ComicAdapter(Long id, String name, ImageView image, String synopsis, int number, int page, int anhoPublication){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.image = image;
        this.synopsis = new SimpleStringProperty(synopsis);
        this.number = new SimpleIntegerProperty(number);
        this.page = new SimpleIntegerProperty(page);
        this.anhoPublication = new SimpleIntegerProperty(anhoPublication);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSynopsis() {
        return synopsis.get();
    }

    public void setSynopsis(String synopsis) {
        this.synopsis.set(synopsis);
    }

    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public int getPage() {
        return page.get();
    }

    public void setPage(int page) {
        this.page.set(page);
    }

    public int getAnhoPublication() {
        return anhoPublication.get();
    }

    public void setAnhoPublication(int anhoPublication) {
        this.anhoPublication.set(anhoPublication);
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public ImageView getImage() { return image; }

    public void setImage(ImageView image){ this.image = image; }
}
