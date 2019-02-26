package com.example.demo.interfaceadapters.dataaccess;

import com.example.demo.businessrules.entities.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Consumer, String> {
    Consumer findByDocument(String document);
}
