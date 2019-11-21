package com.example.demo.businessrules.entities.exceptions;

public class Error {

    String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
