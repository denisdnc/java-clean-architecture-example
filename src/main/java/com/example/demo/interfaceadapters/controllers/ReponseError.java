package com.example.demo.interfaceadapters.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReponseError {

    private String message;

    public ReponseError() {
    }

    public ReponseError(String message) {
        this.message = message;
    }

}
