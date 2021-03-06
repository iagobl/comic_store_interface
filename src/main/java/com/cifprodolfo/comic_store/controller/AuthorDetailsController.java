package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.model.Author;
import com.cifprodolfo.comic_store.services.AuthorServices;
import com.cifprodolfo.comic_store.services.AuthorListServices;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
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

public class AuthorDetailsController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private ImageView imageViewAuthor;
    private boolean newImage = false;
    private String pathImage;
    private AuthorAdapter authorAdapter;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
    public AuthorDetailsController() {}

    public void initialize() {}

    public void initData(AuthorAdapter author){
        txtName.setText(author.getName());
        txtSurname.setText(author.getSurname());
        imageViewAuthor.setImage(author.getImage().getImage());
        authorAdapter = author;
    }

    public void changeImage() {

        FileChooser fileChooser = new FileChooser();

        try {
            newImage = true;
            Window stage = imageViewAuthor.getScene().getWindow();
            fileChooser.setTitle(resourceBundle.getString("textTitleLabelChangeImage"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if(selectedFile == null) {
                newImage = false;
                return;
            }

            pathImage = selectedFile.toURI().toURL().toString();
            imageViewAuthor.setImage(new Image(pathImage));
            imageViewAuthor.setFitHeight(220);
            imageViewAuthor.setFitWidth(250);

            pathImage = selectedFile.getAbsolutePath();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("textErrorChangeImage"));
            alert.showAndWait();
        }
    }

    public void saveAuthor() {

        try {

            Author newAuthor;
            String nameAuthor = txtName.getText();
            String surnameAuthor = txtSurname.getText();

            if((nameAuthor.isBlank() || nameAuthor.isEmpty()) || (surnameAuthor.isBlank() || surnameAuthor.isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR, resourceBundle.getString("textErrorCubrirCampos"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(pathImage == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(resourceBundle.getString("textErrorNoImage"));
                alert.showAndWait();
                return;
            }

            newAuthor = AuthorServices.saveAuthors(nameAuthor, surnameAuthor);
            if(newAuthor.getId() == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(resourceBundle.getString("textExistAuthor"));
                alert.showAndWait();
                return;
            }

            authorAdapter = new AuthorAdapter(newAuthor.getId(), newAuthor.getName(), null, newAuthor.getSurname(), newAuthor.getAuthorComicList());
            AuthorServices.uploadImage(authorAdapter, pathImage);


            /*if(newImage || pathImage != null) {

            } else {
                AuthorServices.uploadImage(authorAdapter, AuthorServices.class.getResource("images/icon_author.png").getPath());
            }*/

            AuthorListServices.updateDataAuthor();
            Stage stage = (Stage) this.txtName.getScene().getWindow();
            stage.close();

        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("textErrorSaveAuthor"));
            alert.showAndWait();
        }

    }

    public void updateAuthor() {

        try{

            String nameUpdate = txtName.getText();
            String surnameUpdate = txtSurname.getText();

            if((nameUpdate.isBlank() || nameUpdate.isEmpty()) || (surnameUpdate.isBlank() || surnameUpdate.isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR, resourceBundle.getString("textErrorCubrirCampos"), ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(newImage){
                AuthorServices.uploadImage(authorAdapter, pathImage);
            }

            authorAdapter.setName(nameUpdate);
            authorAdapter.setSurname(surnameUpdate);

            AuthorServices.putAuthors(authorAdapter);

            AuthorListServices.updateDataAuthor();
            Stage stage = (Stage) this.txtName.getScene().getWindow();
            stage.close();

        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("textErrorUpdateAuthor"));
            alert.showAndWait();
        }
    }

    public void cancelButton() {
        try {
            Stage stage = (Stage) this.txtName.getScene().getWindow();
            stage.close();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("textErrorCloseWindow"));
            alert.showAndWait();
        }
    }

}
