package com.example.demo.interfaceadapters.controllers;

import com.example.demo.businessrules.entities.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(basePackages = {"com.example.demo"})
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseErrorWrapper> businessException(BusinessException e) {

        ResponseErrorWrapper responseErrorWrapper = new ResponseErrorWrapper();
        List<ReponseError> errors = new ArrayList<>();
        responseErrorWrapper.setErrors(errors);

        e.getErrors().forEach(error -> errors.add(new ReponseError(error.getMessage())));

        return new ResponseEntity<>(responseErrorWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
