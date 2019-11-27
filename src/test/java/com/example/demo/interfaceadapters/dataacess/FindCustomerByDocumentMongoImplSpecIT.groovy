package com.example.demo.interfaceadapters.dataacess

import com.example.demo.businessrules.entities.Customer
import com.example.demo.businessrules.usecases.steps.FindCustomerByDocument
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification

@SpringBootTest
class FindCustomerByDocumentMongoImplSpecIT extends Specification {
    /** helpers */
    @Autowired
    MongoTemplate mongoTemplate

    /** component to test */
    @Autowired
    FindCustomerByDocument findCustomerByDocument

    def setup() {
        mongoTemplate.dropCollection("customer")
    }

    def "Encontrar um usuário pelo documento" () {
        given: "Um usuário existente"
        Customer customer = new Customer("Gustavo","12312312323")
        mongoTemplate.insert(customer, "customer")

        when: "Eu tento buscar um usuário"
        Optional<Customer> result = findCustomerByDocument.execute("12312312323")

        then: "Eu recebo um usuário válido"
        result.isPresent()
        result.get().nome == "Gustavo"
        result.get().documento == "12312312323"
    }

    def "Não encontrar um usuário pelo documento" () {
        when: "Eu tento buscar um usuário"
        Optional<Customer> result = findCustomerByDocument.execute("12312312323")

        then: "Eu não recebo um usuário"
        !result.isPresent()
    }
}
