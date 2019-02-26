package com.example.demo.businessrules.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Consumer {

    private String name;
    private String document;

    public Consumer() {
    }

    public Consumer(String name, String document) {
        this.name = name;
        this.document = document;
    }

    public boolean valid() {
        return this.validate().isEmpty();
    }

    public List<Error> validate() {
        List<Error> errors = new ArrayList<>();

        if (this.name == null) {
            errors.add(new Error("name is mandatory"));
        }

        if (this.document == null) {
            errors.add(new Error("document is mandatory"));
        }

        return errors;
    }
}
