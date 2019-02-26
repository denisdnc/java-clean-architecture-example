package com.example.demo.businessrules.entities;

import lombok.Getter;

@Getter
public class Score {
    private Long value;

    public Score() {
    }

    public Score(Long value) {
        this.value = value;
    }
}
