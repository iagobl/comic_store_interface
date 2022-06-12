package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.tabla_adapter.ComicAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.awt.*;


public class ComicViewController {

    @FXML
    private TableView tableComics;
    @FXML
    private TableColumn<ComicAdapter, String> lblImageComic;
    @FXML
    private TableColumn<ComicAdapter, String> lblNameComic;
    @FXML
    private TableColumn<ComicAdapter, Integer> lblNumberComic;
    @FXML
    private TableColumn<ComicAdapter, Integer> lblPagesComic;
    @FXML
    private TableColumn<ComicAdapter, Integer> lblPublicationComic;

    public ComicViewController(){

    }

    public void initialize(){

        ImageView photoExample = new ImageView(new Image(this.getClass().getResourceAsStream("/images/icon_photo.png")));
        photoExample.setFitWidth(60);
        photoExample.setFitHeight(60);
        ObservableList<ComicAdapter> data = FXCollections.observableArrayList(
                new ComicAdapter(1321312L, "iago", photoExample, "lkjlfdf", 2, 322, 123)
        );

        lblImageComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("image"));
        /*lblImageComic.setCellFactory(new Callback<TableColumn<ComicAdapter, String>, TableCell<ComicAdapter, String>>() {
            @Override
            public TableCell<ComicAdapter, String> call(TableColumn<ComicAdapter, String> comicAdapterStringTableColumn) {
                TableCell<ComicAdapter, String> tc = new TableCell<>();
                tc.setAlignment(Pos.CENTER);
                return tc;
            }
        });*/

        lblNameComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("name"));
        /*lblNameComic.setCellFactory(new Callback<TableColumn<ComicAdapter, String>, TableCell<ComicAdapter, String>>() {
            @Override
            public TableCell<ComicAdapter, String> call(TableColumn<ComicAdapter, String> comicAdapterStringTableColumn) {
                TableCell<ComicAdapter, String> tc = new TableCell<>();
                tc.setAlignment(Pos.CENTER);
                return tc;
            }
        });*/
        lblNumberComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("number"));
        lblPagesComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("page"));
        lblPublicationComic.setCellValueFactory(new PropertyValueFactory<ComicAdapter, Integer>("number"));

        tableComics.setItems(data);
        tableComics.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
}
