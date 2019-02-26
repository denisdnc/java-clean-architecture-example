package com.example.demo.interfaceadapters.controllers;

import com.example.demo.businessrules.entities.Consumer;
import com.example.demo.businessrules.entities.Score;
import com.example.demo.businessrules.usecases.CheckConsumerScore;
import com.example.demo.businessrules.usecases.RegisterConsumer;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    private RegisterConsumer registerConsumer;
    private CheckConsumerScore checkConsumerScore;

    public ConsumerController(RegisterConsumer registerConsumer, CheckConsumerScore checkConsumerScore) {
        this.registerConsumer = registerConsumer;
        this.checkConsumerScore = checkConsumerScore;
    }

    @PostMapping(value = "/consumers")
    public Consumer register(@RequestBody Consumer consumer) {
        return registerConsumer.execute(consumer);
    }

    @GetMapping(value = "/consumers/{document}/score")
    public Score getScore(@PathVariable(value = "document") String document) {
        return checkConsumerScore.execute(document);
    }

}
