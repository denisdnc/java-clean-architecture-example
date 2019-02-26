package com.example.demo.businessrules.usecases.steps;

import com.example.demo.businessrules.entities.Score;

import java.util.Optional;

public interface GetScore {
    Optional<Score> execute(String userDocument);
}
