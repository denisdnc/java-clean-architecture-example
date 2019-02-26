package com.example.demo.interfaceadapters.restintegrations

import com.example.demo.businessrules.entities.Score
import com.example.demo.businessrules.usecases.steps.GetScore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class GetScoreRestImplSpecIT extends Specification {

    /** component to test */
    @Autowired
    GetScore getSerasaScore

    def "get serasa score with success"() {

        given: "a valid document"

        String document = "39581190855"

        when: "get serasa score"

        Optional<Score> score = getSerasaScore.execute(document)

        then: "should return the score"

        score.isPresent()
        score.get().value >= 0L
        score.get().value <= 100L

    }

}
