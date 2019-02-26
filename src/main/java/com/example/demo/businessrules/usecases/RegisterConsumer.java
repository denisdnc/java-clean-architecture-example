package com.example.demo.businessrules.usecases;

import com.example.demo.businessrules.entities.Consumer;
import com.example.demo.businessrules.entities.exceptions.BusinessException;
import com.example.demo.businessrules.usecases.steps.SaveConsumer;

public class RegisterConsumer {

    private SaveConsumer saveConsumer;

    public RegisterConsumer(SaveConsumer saveConsumer) {
        this.saveConsumer = saveConsumer;
    }

    public Consumer execute(Consumer consumer) {

        if (!consumer.valid()) {
            throw new BusinessException(consumer.validate());
        }

        saveConsumer.execute(consumer);

        return consumer;
    }
}
