package com.example.demo.businessrules.entities.exceptions;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.businessrules.entities.exceptions.Error;

public class BusinessException extends RuntimeException {
    List<Error> errors = new ArrayList<>();

    public BusinessException(List<Error> errors) {
        this.errors = errors;
        //this.errors.add(new Error("customer already exists"));
    }

    public List<Error> getErrors() {
        return errors;
    }


}
