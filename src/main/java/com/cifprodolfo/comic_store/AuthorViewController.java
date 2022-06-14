package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.services.GetAuthorList;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class AuthorViewController {

    @FXML
    private TableView tableAuthor;
    @FXML
    private TableColumn<AuthorAdapter, String> lblImageAuthor;
    @FXML
    private TableColumn<AuthorAdapter, String> lblNameAuthor;
    @FXML
    private TableColumn<AuthorAdapter, String> lblSurnameAuthor;

    public AuthorViewController() {}

    public void initialize(){

        ObservableList<AuthorAdapter> data = FXCollections.observableArrayList();
        data = GetAuthorList.getDataAuthors();

        lblImageAuthor.setCellValueFactory(new PropertyValueFactory<AuthorAdapter, String>("image"));
        lblNameAuthor.setCellValueFactory(new PropertyValueFactory<AuthorAdapter, String>("name"));
        lblSurnameAuthor.setCellValueFactory(new PropertyValueFactory<AuthorAdapter, String>("surname"));

        tableAuthor.setItems(data);
        tableAuthor.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
