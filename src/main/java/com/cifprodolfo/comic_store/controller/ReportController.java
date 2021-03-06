package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.HomeController;
import com.cifprodolfo.comic_store.model.report_model.AutorReport;
import com.cifprodolfo.comic_store.model.report_model.CollectionReport;
import com.cifprodolfo.comic_store.model.report_model.ComicReport;
import com.cifprodolfo.comic_store.services.AuthorListServices;
import com.cifprodolfo.comic_store.services.CollectionListServices;
import com.cifprodolfo.comic_store.services.CollectionServices;
import com.cifprodolfo.comic_store.services.ComicListServices;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.util.*;

public class ReportController{

    @FXML
    private Button btnReportCollectionName;
    @FXML
    private Button btnReportComics;
    @FXML
    private Button btnReportAuthor;
    @FXML
    private Button btnReportComicsName;
    @FXML
    private TextField txtReportAuthorName;
    @FXML
    private TextField txtReportComicsName;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("language/language");
    public void initialize(){
        btnReportCollectionName.setOnMouseClicked(mouseEvent -> {
            reportCollections();
        });

        btnReportComics.setOnMouseClicked(mouseEvent -> {
            reportComic();
        });

        btnReportAuthor.setOnMouseClicked(mouseEvent -> {
            if(txtReportAuthorName.getText().isEmpty() || txtReportAuthorName.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText(resourceBundle.getString("textReportNameAuthor"));
                alert.showAndWait();
                return;
            }
            reportAuthorName(txtReportAuthorName.getText());

            txtReportAuthorName.setText("");

        });

        btnReportComicsName.setOnMouseClicked(mouseEvent -> {
            if(txtReportComicsName.getText().isEmpty() || txtReportComicsName.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText(resourceBundle.getString("textReportNameAuthor"));
                alert.showAndWait();
                return;
            }

            reportComicName(txtReportComicsName.getText());
            txtReportComicsName.setText("");
        });
    }

    public void reportCollections() {

        InputStream in;

        try {
            System.out.println(Locale.getDefault());

            if(Locale.getDefault().getLanguage().equals("es")){
                in = HomeController.class.getResourceAsStream("reports/CollectionReport.jrxml");
            } else {
                in = HomeController.class.getResourceAsStream("reports/CollectionReportGL.jrxml");
            }

            List<CollectionReport> listReport = CollectionListServices.collectionList();
            if(listReport.size() == 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(resourceBundle.getString("textErrorNotFoundReportCollection"));
                alert.showAndWait();
                return;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("logo", ClassLoader.getSystemResourceAsStream("images/icon_report.png"));
            JasperReport jasperReport = JasperCompileManager.compileReport(in);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(CollectionListServices.collectionList()));

            JasperViewer.viewReport(jasperPrint, false);


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("txtErrorShowReport"));
            alert.showAndWait();
        }
    }

    public void reportComic() {
        InputStream in;

        try {

            if(Locale.getDefault().getLanguage().equals("es")){
                in = HomeController.class.getResourceAsStream("reports/ComicReport.jrxml");
            } else {
                in = HomeController.class.getResourceAsStream("reports/ComicReportGL.jrxml");
            }

            List<ComicReport> listReport = ComicListServices.comicList();
            if(listReport.size() == 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(resourceBundle.getString("textErrorNotFoundReportComic"));
                alert.showAndWait();
                return;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("logo", ClassLoader.getSystemResourceAsStream("images/icon_report.png"));
            JasperReport jasperReport = JasperCompileManager.compileReport(in);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(listReport));

            JasperViewer.viewReport(jasperPrint, false);


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("txtErrorShowReport"));
            alert.showAndWait();
        }
    }

    public void reportAuthorName(String name) {
        InputStream in;

        try {

            if(Locale.getDefault().getLanguage().equals("es")){
                in = HomeController.class.getResourceAsStream("reports/AuthorReport.jrxml");
            } else {
                in = HomeController.class.getResourceAsStream("reports/AuthorReportGL.jrxml");
            }

            List<AutorReport> listReport = AuthorListServices.authorList(name);
            if(listReport.size() == 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(resourceBundle.getString("txtErrorNotFoundAuthor"));
                alert.showAndWait();
                return;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("logo", ClassLoader.getSystemResourceAsStream("images/icon_report.png"));
            JasperReport jasperReport = JasperCompileManager.compileReport(in);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(listReport));

            JasperViewer.viewReport(jasperPrint, false);


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("txtErrorShowReport"));
            alert.showAndWait();
        }
    }

    public void reportComicName(String name) {
        InputStream in;

        try {

            if(Locale.getDefault().getLanguage().equals("es")){
                in = HomeController.class.getResourceAsStream("reports/ComicReport.jrxml");
            } else{
                in = HomeController.class.getResourceAsStream("reports/ComicReportGL.jrxml");
            }

            List<ComicReport> listReport = ComicListServices.comicReportListName(name);
            if(listReport.size() == 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(resourceBundle.getString("txtErrotNotFoundComic"));
                alert.showAndWait();
                return;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("logo", ClassLoader.getSystemResourceAsStream("images/icon_report.png"));
            JasperReport jasperReport = JasperCompileManager.compileReport(in);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(listReport));

            JasperViewer.viewReport(jasperPrint, false);


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(resourceBundle.getString("txtErrorShowReport"));
            alert.showAndWait();
        }
    }
}
