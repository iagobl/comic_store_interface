package com.cifprodolfo.comic_store.controller;

import com.cifprodolfo.comic_store.HomeController;
import com.cifprodolfo.comic_store.services.CollectionListServices;
import com.cifprodolfo.comic_store.services.CollectionServices;
import com.cifprodolfo.comic_store.services.ComicListServices;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ReportController{

    @FXML
    private Button btnReportCollectionName;
    @FXML
    private Button btnReportComics;

    public void initialize(){
        btnReportCollectionName.setOnMouseClicked(mouseEvent -> {
            reportCollections();
        });

        btnReportComics.setOnMouseClicked(mouseEvent -> {
            reportComic();
        });
    }

    public void reportCollections() {

        InputStream in = HomeController.class.getResourceAsStream("reports/CollectionReport.jrxml");

        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("logo", ClassLoader.getSystemResourceAsStream("images/icon_report.png"));
            JasperReport jasperReport = JasperCompileManager.compileReport(in);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(CollectionListServices.collectionList()));

            JasperViewer.viewReport(jasperPrint, false);


        } catch (Exception e) {

        }
    }

    public void reportComic() {
        InputStream in = HomeController.class.getResourceAsStream("reports/ComicReport.jrxml");

        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("logo", ClassLoader.getSystemResourceAsStream("images/icon_report.png"));
            JasperReport jasperReport = JasperCompileManager.compileReport(in);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(ComicListServices.comicList()));

            JasperViewer.viewReport(jasperPrint, false);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
