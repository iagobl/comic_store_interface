package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.model.Author;
import com.cifprodolfo.comic_store.model.Collection;
import com.cifprodolfo.comic_store.model.Comic;
import com.cifprodolfo.comic_store.services.AuthorListServices;
import com.cifprodolfo.comic_store.services.CollectionListServices;
import com.cifprodolfo.comic_store.services.ComicListServices;
import com.cifprodolfo.comic_store.services.ComicServices;
import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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

    public ComicDetailsController() {}

    public void initialize() {}

    public void initData(ComicAdapter comicAdapter) {
           txtNameDetailsComic.setText(comicAdapter.getName());
           txtNumberDetailsComic.setText(String.valueOf(comicAdapter.getNumber()));
           txtPageDetailsComic.setText(String.valueOf(comicAdapter.getPage()));
           txtAnhoPublicationDetailsComic.setText(String.valueOf(comicAdapter.getAnhoPublication()));
           txtDateDetailsComic.setText(String.valueOf(comicAdapter.getDateAcquistion()));
           txtStateDetailsComic.setText(comicAdapter.getState());
           txtPriceDetailsComic.setText(String.valueOf(comicAdapter.getPrice()));
           txtSynopsisDetailsComics.setText(comicAdapter.getSynopsis());
           imageViewComicDetails.setImage(comicAdapter.getImage().getImage());
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
        Stage stage = new Stage();

        try {
            newImage = true;
            stage.setTitle("Seleccione la foto");
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
            alert.setContentText("Error al cambiar la imagén");
            alert.showAndWait();
        }
    }

    public void saveAuthor() {

        try {

            Comic comic;
            String name = txtNameDetailsComic.getText();
            String synopsis = txtSynopsisDetailsComics.getText();
            String number = txtNumberDetailsComic.getText();
            String page = txtPageDetailsComic.getText();
            String tape = txtTapeDetailsComic.getText();
            String date = txtDateDetailsComic.getText();
            String anho = txtAnhoPublicationDetailsComic.getText();
            String state = txtStateDetailsComic.getText();
            String price = txtPriceDetailsComic.getText();
            String job = txtJobComic.getText();

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
                    (job.isBlank() || job.isEmpty())) {

                Alert alert = new Alert(Alert.AlertType.WARNING, "Cubre todos los campos", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(author == null || collection == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecciona un elemento en los combos", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            comic = ComicServices.saveComics(name, synopsis, number, page, tape, date, anho, state, price);
            comicAdapter = new ComicAdapter(
                    comic.getId(),
                    comic.getName(),
                    null,
                    comic.getSynopsis(),
                    comic.getNumber(),
                    comic.getPage(),
                    comic.getTapa(),
                    comic.getAnhoPublication(),
                    comic.getDateAcquistion(),
                    comic.getState(),
                    comic.getPrice(),
                    comic.getAuthorComic()
            );

            if(newImage){
                ComicServices.uploadImage(comicAdapter, pathImage);
            } else {
                ComicServices.uploadImage(comicAdapter, ComicDetailsController.class.getResource("/images/icon_photo.png").getPath());
            }

            Long idAuthor = ((Author) cmbComboAutores.getSelectionModel().getSelectedItem()).getId();
            Long idCollection = ((Collection) cmbComboColecciones.getSelectionModel().getSelectedItem()).getId();




            ComicListServices.updateDataComic();
            Stage stage = (Stage) this.txtDateDetailsComic.getScene().getWindow();
            stage.close();

        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al guardar comic");
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
            alert.setContentText("Error al cerrar la pestaña");
            alert.showAndWait();
        }
    }
}
