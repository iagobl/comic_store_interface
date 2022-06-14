package com.cifprodolfo.comic_store.table_adapter;

import com.cifprodolfo.comic_store.model.AuthorComic;
import javafx.beans.property.*;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.List;

public class ComicAdapter {

    private final LongProperty id;
    private final StringProperty name;
    private ImageView image;
    private final StringProperty synopsis;
    private final IntegerProperty number;
    private final IntegerProperty page;
    private final StringProperty tapa;
    private final IntegerProperty anhoPublication;
    private LocalDate  dateAcquistion;
    private final StringProperty state;
    private final DoubleProperty price;
    private  List<AuthorComic> authorComic;

    public ComicAdapter(){
        this(null, null, null, null, 0, 0, null, 0, null, null, 0.0, null);
    }

    public ComicAdapter(Long id, String name, ImageView image, String synopsis, int number, int page, String tapa, int anhoPublication, LocalDate dateAcquistion, String state, double price, List<AuthorComic> authorComic){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.image = image;
        this.synopsis = new SimpleStringProperty(synopsis);
        this.number = new SimpleIntegerProperty(number);
        this.page = new SimpleIntegerProperty(page);
        this.tapa = new SimpleStringProperty(tapa);
        this.anhoPublication = new SimpleIntegerProperty(anhoPublication);
        this.dateAcquistion = dateAcquistion;
        this.state = new SimpleStringProperty(state);
        this.price = new SimpleDoubleProperty(price);
        this.authorComic = authorComic;
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

    public String getTapa() {return tapa.get(); }

    public void setTapa(String tapa) { this.tapa.set(tapa); }

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

    public LocalDate getDateAcquistion() { return dateAcquistion; }

    public void setDateAcquistion(LocalDate dateAcquistion) { this.dateAcquistion = dateAcquistion; }

    public String getState() { return state.get(); }

    public void setState(String state) { this.state.set(state); }

    public double getPrice() { return price.get(); }

    public void setPrice(double price) { this.price.set(price); }

    public List<AuthorComic> getAuthorComic() { return authorComic; }

    public void setAuthorComic(List<AuthorComic> authorComic) { this.authorComic = authorComic; }
}
