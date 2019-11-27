package com.example.demo.interfaceadapters.vo;

public class CustomerVO {

    public CustomerVO() {
    }

    public CustomerVO(String name, String document) {
        this.setName(name);
        this.setDocument(document);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    private String name;

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    private String document;
}
