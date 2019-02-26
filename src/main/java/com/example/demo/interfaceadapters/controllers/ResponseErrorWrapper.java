package com.example.demo.interfaceadapters.controllers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseErrorWrapper {

    private List<ReponseError> errors;

    public ResponseErrorWrapper() {
    }
}
