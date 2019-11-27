package com.example.demo.interfaceadapters

import com.example.demo.businessrules.entities.Customer
import com.example.demo.interfaceadapters.vo.CustomerVO
import spock.lang.Specification

class CustomerConverterSpec extends Specification {

    CustomerConverter customerConverter = new CustomerConverter()
    def "convertendo Customer para json"(){

        given: "Um Customer"
        Customer customer = new Customer("Picanha","12912478")

        when:"convertido para customerVO"
        CustomerVO customervo = customerConverter.convertToVO(customer);

        then: "os atributos devem ser convertidos"
        customervo.name == "Picanha"
        customervo.document == "12912478"
    }
}
