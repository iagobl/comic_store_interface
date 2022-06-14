package com.cifprodolfo.comic_store.table_adapter;

import com.cifprodolfo.comic_store.model.AuthorComic;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

import java.util.List;


public class AuthorAdapter {

    private final LongProperty id;
    private final StringProperty name;
    private ImageView image;
    private final StringProperty surname;
    private List<AuthorComic> authorComicList;

    public AuthorAdapter() { this(null, null, null, null, null); }

    public AuthorAdapter(Long id, String name, ImageView image, String surname, List<AuthorComic> authorComicList){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.image = image;
        this.surname = new SimpleStringProperty(surname);
        this.authorComicList = authorComicList;
    }

    public long getId() { return id.get(); }

    public void setId(long id) { this.id.set(id); }

    public String getName() { return name.get(); }

    public void setName(String name) { this.name.set(name); }

    public ImageView getImage() { return image; }

    public void setImage(ImageView image) { this.image = image; }

    public String getSurname() { return surname.get(); }

    public void setSurname(String surname) { this.surname.set(surname); }

    public List<AuthorComic> getAuthorComicList() { return authorComicList; }

    public void setAuthorComicList(List<AuthorComic> authorComicList) { this.authorComicList = authorComicList; }
}
