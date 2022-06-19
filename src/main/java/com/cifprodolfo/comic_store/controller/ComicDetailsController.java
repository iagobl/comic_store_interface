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
import java.time.LocalDate;
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
    private ComicAdapter comic;

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
        txtAuthorName.setDisable(true);
        txtCollectionName.setDisable(true);

        comic = comicAdapter;

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
            int numberCast;
            int pageCast;
            int anhoCast;
            double priceCast;
            int timeDedicatedCast;

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
            try {
                numberCast = Integer.valueOf(number);
                pageCast = Integer.valueOf(page);
                priceCast = Double.valueOf(price);
                anhoCast = Integer.valueOf(anho);
                timeDedicatedCast = Integer.valueOf(timeDedicated);

            } catch (NumberFormatException e2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(resourceBundle.getString("textCastNumber"));
                alert.showAndWait();
                return;
            }

            if(numberCast < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING, resourceBundle.getString("NumberComicException"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(pageCast < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING, resourceBundle.getString("NumberComicException"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(priceCast < 0.0){
                Alert alert = new Alert(Alert.AlertType.WARNING, resourceBundle.getString("NumberComicException"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(anhoCast < 1000 || anhoCast > 2022){
                Alert alert = new Alert(Alert.AlertType.WARNING, resourceBundle.getString("textErrorAnhoPublication"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(timeDedicatedCast < 0) {
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

    public void updateComic(){

        try {

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

            if(newImage) {
                ComicServices.uploadImage(comic.getId(), pathImage);
            }

            comic.setName(name);
            comic.setSynopsis(synopsis);
            comic.setNumber(Integer.parseInt(number));
            comic.setPage(Integer.parseInt(page));
            comic.setTapa(tape);
            comic.setDateAcquistion(LocalDate.parse(date));
            comic.setAnhoPublication(Integer.parseInt(anho));
            comic.setState(state);
            comic.setPrice(Double.parseDouble(price));
            comic.setTimeDedicated(Integer.parseInt(timeDedicated));

            NewComicAdapter newComicAdapter = ComicServices.putComics(comic);
            if(newComicAdapter == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(resourceBundle.getString("textNameDuplicateComic"));
                alert.showAndWait();
                return;
            }

            ComicListServices.updateDataComic();
            Stage stage = (Stage) this.txtDateDetailsComic.getScene().getWindow();
            stage.close();


        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("textErrorUpdateComic"));
            alert.showAndWait();
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
