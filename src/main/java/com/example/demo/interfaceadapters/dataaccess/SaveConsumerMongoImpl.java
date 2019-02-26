package com.example.demo.interfaceadapters.dataaccess;

import com.example.demo.businessrules.entities.Consumer;
import com.example.demo.businessrules.usecases.steps.SaveConsumer;
import org.springframework.stereotype.Repository;

@Repository
public class SaveConsumerMongoImpl implements SaveConsumer {

    private ConsumerRepository consumerRepository;

    public SaveConsumerMongoImpl(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Override
    public Consumer execute(Consumer consumer) {
        try {
            return consumerRepository.save(consumer);
        } catch (Exception e) {
            throw new DataAccessException();
        }
    }
}
