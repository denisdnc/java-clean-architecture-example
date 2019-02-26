package com.example.demo.interfaceadapters.dataaccess

import com.example.demo.businessrules.entities.Consumer
import com.example.demo.businessrules.usecases.steps.FindConsumerByDocument
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification

@SpringBootTest
class FindConsumerByDocumentMongoImplSpecIT extends Specification {

    /** helpers */
    @Autowired
    MongoTemplate mongoTemplate

    /** component to test */
    @Autowired
    FindConsumerByDocument findConsumerByDocument

    def setup() {
        mongoTemplate.dropCollection("consumer")
    }

    def "find consumer with success"() {

        given: "a consumer in data base"

        Consumer consumer = new Consumer("Jack Daniels", "39581190845")
        mongoTemplate.insert(consumer, "consumer")

        when: "find consumer bu document"

        Consumer result = findConsumerByDocument.execute("39581190845").get()

        then: "consumer should match attributes"

        result.name == "Jack Daniels"
        result.document == "39581190845"

    }

}
