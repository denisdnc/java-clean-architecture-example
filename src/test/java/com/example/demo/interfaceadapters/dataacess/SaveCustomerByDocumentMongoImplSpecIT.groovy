package com.example.demo.interfaceadapters.dataacess

import com.example.demo.businessrules.entities.Customer
import com.example.demo.businessrules.usecases.steps.SaveCustomer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import spock.lang.Specification

@SpringBootTest
class SaveCustomerByDocumentMongoImplSpecIT extends Specification {
    /** helpers */
    @Autowired
    MongoTemplate mongoTemplate

    /** component to test */
    @Autowired
    SaveCustomer saveCustomer

    def setup() {
        mongoTemplate.dropCollection("customer")
    }

    def "Salvar usuario com sucesso"(){

        given: "Dado um usuário"
        Customer customer = new Customer("Gostavo","12312312323")

        when: "usuário é salvo"

        saveCustomer.execute(customer)

        then: "deve retornar o usuário salvo"
        Customer result = mongoTemplate.findOne(
                Query.query(Criteria.where("documento").is("12312312323")), Customer.class, "customer")

        result.nome == "Gostavo"
        result.documento == "12312312323"
    }
}
