package com.example.demo.interfaceadapters.restintegrations;

import com.example.demo.businessrules.entities.Score;
import com.example.demo.businessrules.usecases.steps.GetScore;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetScoreRestImpl implements GetScore {

    private SerasaHttpClient serasaHttpClient;

    public GetScoreRestImpl(SerasaHttpClient serasaHttpClient) {
        this.serasaHttpClient = serasaHttpClient;
    }

    @Override
    public Optional<Score> execute(String userDocument) {
        return Optional.of(serasaHttpClient.getScore(userDocument));
    }
}
