package com.example.demo.interfaceadapters.dataaccess

import com.example.demo.businessrules.entities.Consumer
import com.example.demo.businessrules.usecases.steps.SaveConsumer
import spock.lang.Specification

class SaveConsumerMongoImplSpec extends Specification {

    /** helpers */
    ConsumerRepository consumerRepository = Mock()

    /** component to test */
    SaveConsumer saveConsumer = new SaveConsumerMongoImpl(consumerRepository)

    def "throw data access exception"() {

        given: "a consumer"

        Consumer consumer = new Consumer("Jack Daniels", "39581190845")

        when: "save consumer"

        saveConsumer.execute(consumer)

        then: "mongo throws error"

        1 * consumerRepository.save(_ as Consumer) >> {
            throw new Exception()
        }

        and: "should be thrown DataAccessException"

        thrown(DataAccessException)

    }

}
