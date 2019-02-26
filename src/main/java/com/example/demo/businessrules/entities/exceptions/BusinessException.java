package com.example.demo.businessrules.entities.exceptions;

import com.example.demo.businessrules.entities.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class BusinessException extends RuntimeException {
    private List<Error> errors;

    public BusinessException(List<Error> errors) {
        this.errors = errors;
    }
}
