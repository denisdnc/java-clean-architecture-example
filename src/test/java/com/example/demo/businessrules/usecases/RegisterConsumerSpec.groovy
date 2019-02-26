package com.example.demo.businessrules.usecases


import com.example.demo.businessrules.entities.exceptions.BusinessException
import com.example.demo.businessrules.entities.Consumer
import com.example.demo.businessrules.usecases.steps.SaveConsumer
import spock.lang.Specification

class RegisterConsumerSpec extends Specification {

    /** dependencies */
    SaveConsumer saveConsumer = Mock()

    /** component to test */
    RegisterConsumer registerConsumer = new RegisterConsumer(saveConsumer)

    def "validate mandatory attributes"() {

        given: "an invalid consumer"

        Consumer consumer = new Consumer(null, null)

        when: "register the consumer"

        registerConsumer.execute(consumer)

        then: "should validate mandatory attributes"

        BusinessException exception = thrown(BusinessException)
        !exception.errors.empty
        exception.errors.size() == 2

        exception.errors.any {
            it.message == "name is mandatory"
        }

        exception.errors.any {
            it.message == "document is mandatory"
        }

        and: "do not save the consumer"

        0 * saveConsumer.execute()
    }

    def "save consumer and return"() {

        given: "a valid consumer"

        Consumer consumer = new Consumer("Jack Daniels", "39581190845")

        when: "register the consumer"

        Consumer result = registerConsumer.execute(consumer)

        then: "save the consumer"

        1 * saveConsumer.execute(_ as Consumer) >> {
            Consumer arg ->
                assert arg.name == "Jack Daniels"
                assert arg.document == "39581190845"
        }

        and: "return the consumer"

        result.name == "Jack Daniels"
        result.document == "39581190845"
    }

}
