package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.tabla_adapter.ComicAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ComicViewController {

    @FXML
    private TableView tableComics;

    public ComicViewController(){

    }

    public void initialize(){

        ObservableList<ComicAdapter> data = FXCollections.observableArrayList(
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123),
                new ComicAdapter(1321312L, "iago", "asdkfasdf", "lkjlfdf", 2, 322, 123)
        );

        TableColumn ImageCol = new TableColumn("Imagen");
        ImageCol.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("image"));
        TableColumn NameCol = new TableColumn("Nombre");
        NameCol.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("name"));
        TableColumn NumberCol = new TableColumn("Numero");
        NumberCol.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("number"));
        TableColumn PageCol = new TableColumn("Pagina");
        PageCol.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("page"));
        TableColumn AnhoPublication = new TableColumn("Publicación");
        AnhoPublication.setCellValueFactory(new PropertyValueFactory<ComicAdapter, String>("number"));

        tableComics.setItems(data);
        tableComics.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableComics.getColumns().addAll(ImageCol, NameCol, NumberCol, PageCol, AnhoPublication);

    }
}
