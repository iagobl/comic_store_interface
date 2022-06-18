package com.cifprodolfo.comic_store.model.report_model;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

public class CollectionReport {

    private Long id;
    private String name;
    private byte[] image;
    private String editorial;
    private List<ComicReport> comicList;
    private JRBeanCollectionDataSource comicdataSource;

    public CollectionReport() {}

    public CollectionReport(Long id, String name, byte[] image, String editorial, List<ComicReport> comicList, JRBeanCollectionDataSource comicdataSource) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.editorial = editorial;
        this.comicList = comicList;
        this.comicdataSource = comicdataSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public List<ComicReport> getComicList() {
        return comicList;
    }

    public void setComicList(List<ComicReport> comicList) {
        this.comicList = comicList;
    }

    public JRBeanCollectionDataSource getComicdataSource() {
        return new JRBeanCollectionDataSource(comicList, false);
    }

    public void setComicdataSource(JRBeanCollectionDataSource comicdataSource) {
        this.comicdataSource = comicdataSource;
    }
}
