package com.example.demo.interfaceadapters.controllers

import com.example.demo.businessrules.entities.exceptions.BusinessException
import com.example.demo.businessrules.entities.Consumer
import com.example.demo.businessrules.entities.Error
import com.example.demo.businessrules.entities.Score
import com.example.demo.businessrules.usecases.CheckConsumerScore
import com.example.demo.businessrules.usecases.RegisterConsumer
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class ConsumerControllerSpec extends Specification {

    /** helpers */
    ObjectMapper objectMapper = new ObjectMapper()
    private MockMvc mockMvc

    /** dependencies */
    RegisterConsumer registerConsumer = Mock()
    CheckConsumerScore checkConsumerScore = Mock()

    /** component to test */
    ConsumerController controller = new ConsumerController(registerConsumer, checkConsumerScore)

    def setup() {
        /** init MockMvc */
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(ExceptionHandlers).build()
    }

    def "do POST on /consumers and get http status 201"() {

        given: "a valid consumer payload"

        Consumer consumer = new Consumer("Jack Daniels", "39581190845")

        when: "do POST"

        MvcResult result = mockMvc.perform(post("/consumers")
                .content(objectMapper.writeValueAsString(consumer))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn()

        then: "should call register consumer"

        1 * registerConsumer.execute(_ as Consumer) >> {
            Consumer parameter ->
                assert parameter.name == "Jack Daniels"
                assert parameter.document == "39581190845"

                parameter
        }

        and: "http response code should be"

        result.response.status == HttpStatus.OK.value()

        and: "response body should match properties"

        Consumer responseBody = objectMapper.readValue(result.response.contentAsString, Consumer.class)

        responseBody.name == "Jack Daniels"
        responseBody.document == "39581190845"

    }

    def "do POST on /consumers and get http status 422"() {

        given: "an invalid consumer payload"

        Consumer consumer = new Consumer()

        when: "do POST"

        MvcResult result = mockMvc.perform(post("/consumers")
                .content(objectMapper.writeValueAsString(consumer))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn()

        then: "should call register consumer and throws business exception"

        1 * registerConsumer.execute(_ as Consumer) >> {
                List<Error> errors = new ArrayList<>()
                errors.add(new Error("name is mandatory"))
                errors.add(new Error("document is mandatory"))
                throw new BusinessException(errors)
        }

        and: "http response code should be"

        result.response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()

        and: "response body should match properties"

        ResponseErrorWrapper responseBody =
                objectMapper.readValue(result.response.contentAsString, ResponseErrorWrapper.class)

        responseBody.errors.size() == 2

        responseBody.errors.any {
            it.message == "name is mandatory"
        }

        responseBody.errors.any {
            it.message == "document is mandatory"
        }

    }

    def "do GET on /consumers/{document}/score and get http status 200"() {

        when: "do GET"

        MvcResult result = mockMvc.perform(get("/consumers/39581190845/score")).andReturn()

        then: "should call check consumer score"

        1 * checkConsumerScore.execute("39581190845") >> new Score(100L)

        and: "http response code should be"

        result.response.status == HttpStatus.OK.value()

        and: "response body should match properties"

        Score responseBody = objectMapper.readValue(result.response.contentAsString, Score.class)

        responseBody.value == 100L

    }

}
