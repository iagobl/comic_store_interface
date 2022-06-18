package com.cifprodolfo.comic_store.table_adapter;

import com.cifprodolfo.comic_store.model.Comic;
import javafx.beans.property.*;
import javafx.scene.image.ImageView;

import java.util.List;

public class CollectionAdapter {

    private final LongProperty id;
    private final StringProperty name;
    private ImageView image;
    private final StringProperty editorial;

    public CollectionAdapter() { this(null, null, null, null); }

    public CollectionAdapter(Long id, String name, ImageView image, String editorial){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.image = image;
        this.editorial = new SimpleStringProperty(editorial);
    }

    public long getId() { return id.get(); }

    public void setId(long id) { this.id.set(id); }

    public String getName() { return name.get(); }

    public void setName(String name) { this.name.set(name); }

    public ImageView getImage() { return image; }

    public void setImage(ImageView image) { this.image = image; }

    public String getEditorial() { return editorial.get(); }

    public void setEditorial(String editorial) { this.editorial.set(editorial); }

}
