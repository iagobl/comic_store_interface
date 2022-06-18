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
    private  String authorName;
    private Long collection_id;

    public ComicAdapter(Long id, String name, ImageView image, String synopsis, Integer number, Integer page, String tapa, Integer anhoPublication, LocalDate dateAcquistion, String state, Double price, String authorName, Long collection_id) {
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
        this.authorName = authorName;
        this.collection_id = collection_id;
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getSynopsis() {
        return synopsis.get();
    }

    public StringProperty synopsisProperty() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis.set(synopsis);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public int getPage() {
        return page.get();
    }

    public IntegerProperty pageProperty() {
        return page;
    }

    public void setPage(int page) {
        this.page.set(page);
    }

    public String getTapa() {
        return tapa.get();
    }

    public StringProperty tapaProperty() {
        return tapa;
    }

    public void setTapa(String tapa) {
        this.tapa.set(tapa);
    }

    public int getAnhoPublication() {
        return anhoPublication.get();
    }

    public IntegerProperty anhoPublicationProperty() {
        return anhoPublication;
    }

    public void setAnhoPublication(int anhoPublication) {
        this.anhoPublication.set(anhoPublication);
    }

    public LocalDate getDateAcquistion() {
        return dateAcquistion;
    }

    public void setDateAcquistion(LocalDate dateAcquistion) {
        this.dateAcquistion = dateAcquistion;
    }

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Long collection_id){
        this.collection_id = collection_id;
    }
}
