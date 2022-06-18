package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.model.Author;
import com.cifprodolfo.comic_store.model.Collection;
import com.cifprodolfo.comic_store.model.Comic;
import com.cifprodolfo.comic_store.model.adapter.NewComicAdapter;
import com.cifprodolfo.comic_store.services.*;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class ComicDetailsController {

    @FXML
    private TextField txtNameDetailsComic;
    @FXML
    private TextField txtNumberDetailsComic;
    @FXML
    private TextField txtPageDetailsComic;
    @FXML
    private TextField txtAnhoPublicationDetailsComic;
    @FXML
    private TextField txtDateDetailsComic;
    @FXML
    private TextField txtStateDetailsComic;
    @FXML
    private TextField txtPriceDetailsComic;
    @FXML
    private TextField txtTapeDetailsComic;
    @FXML
    private TextField txtJobComic;
    @FXML
    private TextField txtCollectionName;
    @FXML
    private TextField txtAuthorName;
    @FXML
    private TextArea txtSynopsisDetailsComics;
    @FXML
    private ImageView imageViewComicDetails;
    @FXML
    private ComboBox cmbComboAutores;
    @FXML
    private ComboBox cmbComboColecciones;
    private boolean newImage = false;
    private String pathImage;
    private ComicAdapter comicAdapter;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");

    public ComicDetailsController() {}

    public void initialize() {}

    public void initData(ComicAdapter comicAdapter, Collection collection) {

        //Collection collectionShow = CollectionListServices.getCollectionById(comicAdapter.)

        txtNameDetailsComic.setText(comicAdapter.getName());
        txtNumberDetailsComic.setText(String.valueOf(comicAdapter.getNumber()));
        txtPageDetailsComic.setText(String.valueOf(comicAdapter.getPage()));
        txtAnhoPublicationDetailsComic.setText(String.valueOf(comicAdapter.getAnhoPublication()));
        txtDateDetailsComic.setText(String.valueOf(comicAdapter.getDateAcquistion()));
        txtStateDetailsComic.setText(comicAdapter.getState());
        txtPriceDetailsComic.setText(String.valueOf(comicAdapter.getPrice()));
        txtSynopsisDetailsComics.setText(comicAdapter.getSynopsis());
        imageViewComicDetails.setImage(comicAdapter.getImage().getImage());
        txtTapeDetailsComic.setText(comicAdapter.getTapa());
        txtAuthorName.setText(comicAdapter.getAuthorName());
        txtJobComic.setText(String.valueOf(comicAdapter.getTimeDedicated()));
        txtCollectionName.setText(collection.getName());

    }

    public void initData2(){
        try {
            ObservableList<Collection> collections = CollectionListServices.getCollectionfromCombo();
            cmbComboColecciones.setItems(collections);

            ObservableList<Author> authors = AuthorListServices.getAuthorfromCombo();
            cmbComboAutores.setItems(authors);


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeImage() {

        FileChooser fileChooser = new FileChooser();


        try {
            newImage = true;
            Window stage = txtSynopsisDetailsComics.getScene().getWindow();
            fileChooser.setTitle(resourceBundle.getString("textTitleLabelChangeImage"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png"));
            File selectFile = fileChooser.showOpenDialog(stage);

            if(selectFile == null) {
                newImage = false;
                return;
            }

            pathImage = selectFile.toURI().toURL().toString();
            imageViewComicDetails.setImage(new Image(pathImage));
            imageViewComicDetails.setFitWidth(450);
            imageViewComicDetails.setFitHeight(430);
            pathImage = selectFile.getAbsolutePath();
        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorChangeImage"));
            alert.showAndWait();
        }
    }

    public void saveAuthor() {

        try {

            NewComicAdapter newComicAdapter;
            String name = txtNameDetailsComic.getText();
            String synopsis = txtSynopsisDetailsComics.getText();
            String number = txtNumberDetailsComic.getText();
            String page = txtPageDetailsComic.getText();
            String tape = txtTapeDetailsComic.getText();
            String date = txtDateDetailsComic.getText();
            String anho = txtAnhoPublicationDetailsComic.getText();
            String state = txtStateDetailsComic.getText();
            String price = txtPriceDetailsComic.getText();
            String timeDedicated = txtJobComic.getText();

            Author author = (Author) cmbComboAutores.getSelectionModel().getSelectedItem();
            Collection collection = (Collection) cmbComboColecciones.getSelectionModel().getSelectedItem();

            if((name.isBlank() || name.isEmpty()) ||
                    (synopsis.isBlank() || synopsis.isEmpty()) ||
                    (number.isBlank() || number.isEmpty()) ||
                    (page.isBlank() || page.isEmpty()) ||
                    (tape.isBlank() || tape.isEmpty()) ||
                    (date.isBlank() || date.isEmpty()) ||
                    (anho.isBlank() || anho.isEmpty()) ||
                    (state.isBlank() || state.isEmpty()) ||
                    (price.isBlank() || price.isEmpty()) ||
                    (timeDedicated.isBlank() || timeDedicated.isEmpty())) {

                Alert alert = new Alert(Alert.AlertType.WARNING, resourceBundle.getString("textErrorCubrirCampos"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(Integer.parseInt(number) < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING, resourceBundle.getString("NumberComicException"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(Integer.parseInt(page) < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING, resourceBundle.getString("NumberComicException"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(Double.valueOf(price) < 0.0){
                Alert alert = new Alert(Alert.AlertType.WARNING, resourceBundle.getString("NumberComicException"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(author == null || collection == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, resourceBundle.getString("textErrorSelectElementCombo"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            Long idAuthor = author.getId();
            Long idComic = collection.getId();

            newComicAdapter = ComicServices.saveComics(name, synopsis, number, page, tape, date, anho, state, price, idComic, timeDedicated, idAuthor);
            if(newComicAdapter.getId() == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(resourceBundle.getString("textExistComic"));
                alert.showAndWait();
                return;
            }

            if(newImage){
                ComicServices.uploadImage(newComicAdapter.getId(), pathImage);
            } else {
                ComicServices.uploadImage(newComicAdapter.getId(), ComicDetailsController.class.getResource("/images/icon_photo.png").getPath());
            }

            //AuthorComicServices.saveAuthorComic(Integer.parseInt(timeDedicated), idAuthor, idComic);

            ComicListServices.updateDataComic();
            Stage stage = (Stage) this.txtDateDetailsComic.getScene().getWindow();
            stage.close();

        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorSaveComic"));
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void cancelButton() {
        try {
            Stage stage = (Stage) this.txtDateDetailsComic.getScene().getWindow();
            stage.close();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorCloseWindow"));
            alert.showAndWait();
        }
    }
}
