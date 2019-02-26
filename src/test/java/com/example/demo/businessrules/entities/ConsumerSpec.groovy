package com.example.demo.businessrules.entities

import spock.lang.Specification


class ConsumerSpec extends Specification {

    def "validate mandatory attributes empty"() {

        given: "an invalid consumer"

        Consumer consumer = new Consumer(null, null)

        when: "validate consumer"

        List<Error> errors = consumer.validate()

        then: "should return errors"

        !errors.isEmpty()
        errors.size() == 2

        errors.any {
            it.message == "name is mandatory"
        }

        errors.any {
            it.message == "document is mandatory"
        }
    }

    def "validate mandatory attributes ok"() {

        given: "a valid consumer"

        Consumer consumer = new Consumer("Jack Daniels", "39581190845")

        when: "validate consumer"

        List<Error> errors = consumer.validate()

        then: "should not return errors"

        errors.isEmpty()
    }

}