package com.example.demo.businessrules.usecases


import com.example.demo.businessrules.entities.exceptions.BusinessException
import com.example.demo.businessrules.entities.Score
import com.example.demo.businessrules.entities.Consumer
import com.example.demo.businessrules.usecases.steps.FindConsumerByDocument
import com.example.demo.businessrules.usecases.steps.GetScore
import spock.lang.Specification

class CheckConsumerScoreSpec extends Specification {

    /** dependencies */
    FindConsumerByDocument findConsumerByDocument = Mock()
    GetScore getSerasaScore = Mock()

    /** component to test */
    private CheckConsumerScore checkConsumerScore = new CheckConsumerScore(findConsumerByDocument, getSerasaScore)

    def "validate if consumer is already registered"() {

        given: "a not registered consumer document"

        String consumerDocument = "39581190845"
        1 * findConsumerByDocument.execute(consumerDocument) >> Optional.empty()

        when: "check consumer score"

        checkConsumerScore.execute(consumerDocument)

        then: "should validate unregistered consumer"

        BusinessException exception = thrown(BusinessException)
        !exception.errors.empty
        exception.errors.size() == 1

        exception.errors.any {
            it.message == "consumer not registered"
        }

    }

    def "find consumer, get SERASA score and return"() {

        given: "a registered consumer document"

        String consumerDocument = "39581190855"
        1 * findConsumerByDocument.execute(consumerDocument) >>
                Optional.of(new Consumer("Jhonny Walker", "39581190855"))

        when: "check consumer score"

        Score result = checkConsumerScore.execute(consumerDocument)

        then: "get SERASA score"

        1 * getSerasaScore.execute("39581190855") >> Optional.of(new Score(100L))

        and: "return the score"

        result.value == 100L

    }

    def "validate SERASA score not found"() {

        given: "a registered consumer document"

        String consumerDocument = "39581190855"
        1 * findConsumerByDocument.execute(consumerDocument) >>
                Optional.of(new Consumer("Jhonny Walker", "39581190855"))

        when: "check consumer score"

       checkConsumerScore.execute(consumerDocument)

        then: "get SERASA score is not found"

        1 * getSerasaScore.execute("39581190855") >> Optional.empty()

        and: "return error"

        BusinessException exception = thrown(BusinessException)
        !exception.errors.empty
        exception.errors.size() == 1

        exception.errors.any {
            it.message == "consumer score not found"
        }

    }

}
