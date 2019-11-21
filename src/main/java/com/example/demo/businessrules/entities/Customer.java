package com.example.demo.businessrules.entities;

import com.example.demo.businessrules.entities.exceptions.Error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    String nome;
    String documento;

    public Customer(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

    public boolean isValid() {
        if (getErrors().size() > 0) {
            return false;
        }

        return true;
    }

    public List<Error> getErrors() {
        List<Error> erros = new ArrayList<>();

        if (this.nome == null ) {
             erros.add(new Error("name is mandatory"));
        }

        if (this.documento == null) {
            erros.add(new Error("document is mandatory"));
        }

        return erros;
    }

    public String getDocument() {
        return this.documento;
    }

    public String getNome() {
        return this.nome;
    }
}
