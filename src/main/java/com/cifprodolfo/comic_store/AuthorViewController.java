package com.cifprodolfo.comic_store;

import com.cifprodolfo.comic_store.model.Author;
import com.cifprodolfo.comic_store.table_adapter.AuthorAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;


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
