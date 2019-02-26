package com.example.demo.interfaceadapters.dataaccess


import com.example.demo.businessrules.entities.Consumer
import com.example.demo.businessrules.usecases.steps.SaveConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import spock.lang.Specification

@SpringBootTest
class SaveConsumerMongoImplSpecIT extends Specification {

    /** helpers */
    @Autowired
    MongoTemplate mongoTemplate

    /** component to test */
    @Autowired
    SaveConsumer saveConsumer

    def setup() {
        mongoTemplate.dropCollection("consumer")
    }

    def "save consumer with success"() {

        given: "a consumer"

        Consumer consumer = new Consumer("Jack Daniels", "39581190845")

        when: "save consumer"

        saveConsumer.execute(consumer)

        then: "consumer should be in the database"

        Consumer result = mongoTemplate.findOne(
                Query.query(Criteria.where("document").is("39581190845")), Consumer.class, "consumer")

        result.name == "Jack Daniels"
        result.document == "39581190845"

    }

}
