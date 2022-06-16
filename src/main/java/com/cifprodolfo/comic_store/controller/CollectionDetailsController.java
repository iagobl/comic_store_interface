package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.model.Collection;
import com.cifprodolfo.comic_store.services.CollectionServices;
import com.cifprodolfo.comic_store.services.CollectionListServices;
import com.cifprodolfo.comic_store.table_adapter.CollectionAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CollectionDetailsController {

    @FXML
    private TextField txtNameDetailsCollection;
    @FXML
    private TextField txtEditorialDetailsCollections;
    @FXML
    private ImageView imageViewCollectionsDetails;
    @FXML
    private ImageView imageSaveCollection;
    private CollectionAdapter collection;
    private boolean newImage = false;
    private String pathImage;
    public CollectionDetailsController() {}

    public void initialize() {}

    public void initData(CollectionAdapter collectionAdapter) {
        txtNameDetailsCollection.setText(collectionAdapter.getName());
        txtEditorialDetailsCollections.setText(collectionAdapter.getEditorial());
        imageViewCollectionsDetails.setImage(collectionAdapter.getImage().getImage());
        collection = collectionAdapter;
    }

    public void changeImage() {

        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();

        try {
            newImage = true;
            stage.setTitle("Seleccione la foto");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png"));
            File selectFile = fileChooser.showOpenDialog(stage);

            if (selectFile == null){
                newImage = false;
                return;
            }

            pathImage = selectFile.toURI().toURL().toString();
            imageViewCollectionsDetails.setImage(new Image(pathImage));
            imageViewCollectionsDetails.setFitWidth(350);
            imageViewCollectionsDetails.setFitHeight(330);

            pathImage = selectFile.getAbsolutePath();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveCollection(){

        try {
            Collection newCollection;
            String nameUpdate = txtNameDetailsCollection.getText();
            String editorialUpdate = txtEditorialDetailsCollections.getText();

            if((nameUpdate.isBlank() || nameUpdate.isEmpty()) || (editorialUpdate.isBlank() || editorialUpdate.isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Los campos no estan completos",ButtonType.OK);
                alert.showAndWait();
                return;
            }

            newCollection = CollectionServices.saveCollections(nameUpdate, editorialUpdate);
            if(newCollection.getId() == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Esa colección ya existe");
                alert.showAndWait();
                return;
            }

            collection = new CollectionAdapter(newCollection.getId(), newCollection.getName(), null, newCollection.getEditorial(), newCollection.getComicList());

            if(newImage){
                CollectionServices.uploadImage(collection, pathImage);
            } else {
                CollectionServices.uploadImage(collection, CollectionServices.class.getResource("/images/icon_photo.png").getPath());
            }

            CollectionListServices.updateDataCollections();
            Stage stage = (Stage) this.txtNameDetailsCollection.getScene().getWindow();
            stage.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCollection() {

        try {
            String nameUpdate = txtNameDetailsCollection.getText();
            String editorialUpdate = txtEditorialDetailsCollections.getText();

            if((nameUpdate.isBlank() || nameUpdate.isEmpty()) || (editorialUpdate.isBlank() || editorialUpdate.isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Los campos no estan completos",ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(newImage){
                CollectionServices.uploadImage(collection, pathImage);
            }

            collection.setName(nameUpdate);
            collection.setEditorial(editorialUpdate);

            CollectionServices.putCollections(collection);

            CollectionListServices.updateDataCollections();
            Stage stage = (Stage) this.txtNameDetailsCollection.getScene().getWindow();
            stage.close();

        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al modificar la coleción");
            alert.showAndWait();
        }
    }

    public void cancelButton() {
        try {
            Stage stage = (Stage) this.txtNameDetailsCollection.getScene().getWindow();
            stage.close();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al cerrar la pestaña");
            alert.showAndWait();
        }
    }
}
