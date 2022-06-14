package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CollectionDetailsController {

    @FXML
    private TextField txtNameDetailsCollection;
    @FXML
    private TextField txtEditorialDetailsCollections;
    @FXML
    private ImageView imageViewCollectionsDetails;

    public CollectionDetailsController() {}

    public void initialize() {}

    public void initData(CollectionAdapter collectionAdapter) {
        txtNameDetailsCollection.setText(collectionAdapter.getName());
        txtEditorialDetailsCollections.setText(collectionAdapter.getEditorial());
        imageViewCollectionsDetails.setImage(collectionAdapter.getImage().getImage());
    }
}
