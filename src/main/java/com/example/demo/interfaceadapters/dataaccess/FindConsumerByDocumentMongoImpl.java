package com.example.demo.interfaceadapters.dataaccess;

import com.example.demo.businessrules.entities.Consumer;
import com.example.demo.businessrules.usecases.steps.FindConsumerByDocument;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FindConsumerByDocumentMongoImpl implements FindConsumerByDocument {

    private ConsumerRepository consumerRepository;

    public FindConsumerByDocumentMongoImpl(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Override
    public Optional<Consumer> execute(String consumerDocument) {
        return Optional.of(consumerRepository.findByDocument(consumerDocument));
    }
}
