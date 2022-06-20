package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.model.Collection;
import com.cifprodolfo.comic_store.model.adapter.NewCollectionAdapater;
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
import javafx.stage.Window;

import java.io.File;
import java.util.ResourceBundle;

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

    ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
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

        try {
            newImage = true;
            Window stage = imageViewCollectionsDetails.getScene().getWindow();
            fileChooser.setTitle(resourceBundle.getString("textTitleLabelChangeImage"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png"));
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("textErrorChangeImage"));
            alert.showAndWait();
        }
    }

    public void saveCollection(){

        try {
            NewCollectionAdapater newCollection;
            String nameUpdate = txtNameDetailsCollection.getText();
            String editorialUpdate = txtEditorialDetailsCollections.getText();

            if((nameUpdate.isBlank() || nameUpdate.isEmpty()) || (editorialUpdate.isBlank() || editorialUpdate.isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR, resourceBundle.getString("textErrorCubrirCampos"),ButtonType.OK);
                alert.showAndWait();
                return;
            }

            newCollection = CollectionServices.saveCollections(nameUpdate, editorialUpdate);
            if(newCollection.getId() == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(resourceBundle.getString("textExistCollection"));
                alert.showAndWait();
                return;
            }

            collection = new CollectionAdapter(newCollection.getId(), newCollection.getName(), null, newCollection.getEditorial());

            System.out.println(pathImage);
            if(newImage || pathImage != null){
                CollectionServices.uploadImage(collection, pathImage);
            } else {
                CollectionServices.uploadImage(collection, CollectionServices.class.getResource("/images/icon_photo.png").getPath());
            }

            CollectionListServices.updateDataCollections();
            Stage stage = (Stage) this.txtNameDetailsCollection.getScene().getWindow();
            stage.close();

        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("textErrorSaveCollection"));
            alert.showAndWait();
        }
    }

    public void updateCollection() {

        try {
            String nameUpdate = txtNameDetailsCollection.getText();
            String editorialUpdate = txtEditorialDetailsCollections.getText();

            if((nameUpdate.isBlank() || nameUpdate.isEmpty()) || (editorialUpdate.isBlank() || editorialUpdate.isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR, resourceBundle.getString("textErrorCubrirCampos"),ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(newImage){
                CollectionServices.uploadImage(collection, pathImage);
            }

            collection.setName(nameUpdate);
            collection.setEditorial(editorialUpdate);

            NewCollectionAdapater newCollectionAdapater = CollectionServices.putCollections(collection);
            if(newCollectionAdapater.getId() == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(resourceBundle.getString("textExistNameCollection"));
                alert.showAndWait();
                return;
            }

            CollectionListServices.updateDataCollections();
            Stage stage = (Stage) this.txtNameDetailsCollection.getScene().getWindow();
            stage.close();

        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("textErrorUpdateCollection"));
            alert.showAndWait();
        }
    }

    public void cancelButton() {
        try {
            Stage stage = (Stage) this.txtNameDetailsCollection.getScene().getWindow();
            stage.close();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("textErrorCloseWindow"));
            alert.showAndWait();
        }
    }
}
