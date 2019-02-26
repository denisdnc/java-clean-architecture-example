package com.example.demo.businessrules.usecases;

import com.example.demo.businessrules.entities.Error;
import com.example.demo.businessrules.entities.Score;
import com.example.demo.businessrules.entities.exceptions.BusinessException;
import com.example.demo.businessrules.usecases.steps.FindConsumerByDocument;
import com.example.demo.businessrules.usecases.steps.GetScore;

import java.util.ArrayList;
import java.util.List;

public class CheckConsumerScore {

    private FindConsumerByDocument findConsumerByDocument;
    private GetScore getScore;

    public CheckConsumerScore(FindConsumerByDocument findConsumerByDocument, GetScore getScore) {
        this.findConsumerByDocument = findConsumerByDocument;
        this.getScore = getScore;
    }

    public Score execute(String consumerDocument) {
        if (!findConsumerByDocument.execute(consumerDocument).isPresent()) {
            throw new BusinessException(buildErrors("consumer not registered"));
        }

        return getScore.execute(consumerDocument)
                .orElseThrow(() -> new BusinessException(buildErrors("consumer score not found")));
    }

    private List<Error> buildErrors(String errorMessage) {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(errorMessage));
        return errors;
    }
}
