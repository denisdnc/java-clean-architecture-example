package com.example.demo.businessrules.usecases

import com.example.demo.businessrules.entities.Customer
import com.example.demo.businessrules.entities.exceptions.BusinessException
import com.example.demo.businessrules.usecases.steps.FindCustomerByDocument
import com.example.demo.businessrules.usecases.steps.SaveCustomer
import spock.lang.Specification

class CreateCustomerSpec extends Specification {

    FindCustomerByDocument findCustomerbyDocument = Mock()
    SaveCustomer saveCustomer = Mock()
    CreateCustomer createCustomer = new CreateCustomer(findCustomerbyDocument,saveCustomer)

    def "Valida usuário" () {
        given: "Um usuário válido"
        Customer customer = new Customer("José", "12345678")

        and: "o usuário existe no sistema"
        1 * findCustomerbyDocument.execute("12345678") >> Optional.empty()

        when: "Um usuário é criado"
        createCustomer.execute(customer)

        then: "O usuário é valido"
        notThrown(BusinessException)
    }

    def "Usuário inválido" () {
        given: "Um usuário inválido"
        Customer customer = new Customer(null, null)

        when: "Um usuário é criado"
        createCustomer.execute(customer)

        then: "O usuário é inválido"
        BusinessException businessException = thrown(BusinessException)

        and: "tamanho da lista de erros deve ser diferente de nulo"
        businessException.errors.size() == 2

        and: "deve conter a mensagem de erro referente ao nome"
        businessException.errors.any() {
            it.message == "name is mandatory"
        }

        and: "deve conter a mensagem de erro referente ao documento"
        businessException.errors.any() {
            it.message == "document is mandatory"
        }
    }

    def "validar usuário existente no sistema" (){

        given: "Um usuário"
        Customer customer = new Customer("José", "Existente")

        when: "Um usuário é criado"
        createCustomer.execute(customer)

        then: "deve buscar o usuario no sistema"
        1 * findCustomerbyDocument.execute("Existente") >> Optional.ofNullable(customer)

        and: "Deve retornar uma businessException"
        BusinessException result = thrown(BusinessException)

        and: "Mensagem de retorno 'Usuario ja existe'"
        result.errors.size() == 1

        and: "A lista deve conter um erro de registro ja existente"
        result.errors.any {
            it.message == "customer already exists"
        }

        and: "não deve salvar o usuario"
        0 * saveCustomer.execute()
    }

    def "salvar usuário não existente no sistema" (){

        given: "Um usuário"
        Customer customer = new Customer("José", "12345678")

        when: "Um usuário é criado"
        Customer result = createCustomer.execute(customer)

        then: "deve buscar o usuario no sistema"
        1 * findCustomerbyDocument.execute("12345678") >> Optional.empty()

        and: "deve salvar o usuario"
        1 * saveCustomer.execute(_ as Customer) >> {
            Customer arg ->
                assert arg.documento == "12345678"
                assert arg.nome == "José"
                assert arg.errors.size() == 0

                arg
        }

        and: "O usuário salvo deve estar preenchido o nome e o documento"
        result.documento == "12345678"
        result.nome == "José"
    }

}
