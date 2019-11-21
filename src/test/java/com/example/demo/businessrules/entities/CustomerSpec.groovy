package com.example.demo.businessrules.entities

import com.example.demo.businessrules.entities.exceptions.Error
import spock.lang.Specification

class CustomerSpec extends Specification {

    def "Validar obrigatoriedade dos campos nome e documento do usuario"() {

        given: "Um usuario com campos nome e documento preenchidos"
        Customer customer = new Customer("Jose", "41888046880")

        when: "Verifico se o usuário está válido"
        boolean result = customer.isValid()

        then: "O usuário deve ser válido"
        result
    }

    def "Validar nome do usuário nulo"() {
        given: "Um usuário com nome não registrado"
        Customer customer = new Customer(null, "41888046880")

        when: "Verifico se o usuário tem nome"
        boolean result = customer.isValid()

        then: "O usuário deve ser inválido"
        !result
    }

    def "Validar document do usuário nulo"() {

        given: "Um usuário com documento não registrado"
        Customer customer = new Customer("Jose", null)

        when: "Verifico se o usuário tem documento"
        boolean result = customer.isValid()

        then: "O usuário deve ser inválido"
        !result
    }

    def "Validar nome e documento do usuário nulo"() {

        given: "Um usuário com nome e documento não registrado"
        Customer customer = new Customer(null, null)

        when: "Verifico se o usuário tem nome e documento"
        boolean result = customer.isValid()

        then: "O usuário deve ser inválido"
        !result
    }

    def "Obter a lista de erros de campos obrigatórios"() {

        given: "Um usuário com nome e documento não registrado"
        Customer customer = new Customer(null, null)

        when: "Obtenho a lista de erros"
        List<Error> result = customer.getErrors()

        then: "A lista deve possuir dois erros"
        result.size() == 2

        and: "A lista deve conter um erro de nome inválido"
        result.any {
            it.message == "name is mandatory"
        }

        and: "A lista deve conter um erro de documento inválido"
        result.any {
            it.message == "document is mandatory"
        }
    }


    def "Obter a lista de erros de um usuário sem nome"() {

        given: "Um usuário sem nome e com documento não registrado"
        Customer customer = new Customer(null, "41888046880")

        when: "Obtenho a lista de erros"
        List<Error> result = customer.getErrors()

        then: "A lista deve possuir um erro"
        result.size() == 1

        and: "A lista deve conter um erro de nome inválido"
        result.any {
            it.message == "name is mandatory"
        }
    }

    def "Obter a lista de erros de um usuário sem documento "() {

        given: "Um usuário sem documento e com nome"
        Customer customer = new Customer("Jose", null)

        when: "Obtenho a lista de erros"
        List<Error> result = customer.getErrors()

        then: "A lista deve possuir um erro"
        result.size() == 1

        and: "A lista deve conter um erro de documento inválido"
        result.any {
            it.message == "document is mandatory"
        }
    }

}
