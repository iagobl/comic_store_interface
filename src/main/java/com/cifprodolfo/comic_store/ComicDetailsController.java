package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.table_adapter.ComicAdapter;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;

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
    private TextArea txtSynopsisDetailsComics;
    @FXML
    private ImageView imageViewComicDetails;

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
           imageViewComicDetails.setImage(comicAdapter.getImage().getImage()); ;
    }
}
