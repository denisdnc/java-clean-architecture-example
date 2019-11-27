package com.example.demo.Controller

import com.example.demo.businessrules.entities.Customer
import com.example.demo.businessrules.usecases.CreateCustomer
import com.example.demo.businessrules.usecases.steps.FindCustomerByDocument
import com.example.demo.businessrules.usecases.steps.SaveCustomer
import com.example.demo.interfaceadapters.CustomerConverter
import com.example.demo.interfaceadapters.controllers.CustomerController
import com.example.demo.interfaceadapters.vo.CustomerVO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class CustomerControlerSpecComp extends Specification {
    /** helpers */
    ObjectMapper objectMapper = new ObjectMapper()
    private MockMvc mockMvc


    /** dependencies */
    FindCustomerByDocument findCustomerByDocument = Mock()
    SaveCustomer saveCustomer = Mock()
    CreateCustomer createCustomer = new CreateCustomer(findCustomerByDocument,saveCustomer)
    CustomerConverter customerConverter = new CustomerConverter()

    /** component to test */
    CustomerController controller = new CustomerController(createCustomer,customerConverter)

    def setup() {
        /** init MockMvc */
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    def "Fazer um post de usuário com sucesso" () {
        given: "dado o request budy"
        CustomerVO customerVO = new CustomerVO("Picanha","12912478")

        when: "quando feito um post na url /costumer"

        MvcResult result = mockMvc.perform(post("/customer")
                .content(objectMapper.writeValueAsString(customerVO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn()

        then: "O HTTP Status deve ser"
        result.response.status == HttpStatus.CREATED.value()

        and: "o serviço findCustomerByDocument deve ser invocado uma vez"
        1 * findCustomerByDocument.execute("12912478") >> Optional.empty()

        and: "deve chamar o save e retornar um customer"
        1 * saveCustomer.execute(_) >> new Customer("Picanha","12912478")

        and: "response body should match properties"

        CustomerVO responseBody = objectMapper.readValue(result.response.contentAsString, CustomerVO.class)

        responseBody.name == "Picanha"
        responseBody.document == "12912478"
    }
}
