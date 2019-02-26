package com.example.demo.businessrules.usecases.steps;

import com.example.demo.businessrules.entities.Consumer;

import java.util.Optional;

public interface FindConsumerByDocument {
    Optional<Consumer> execute(String consumerDocument);
}
