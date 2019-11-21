package com.example.demo.businessrules.usecases;

import com.example.demo.businessrules.entities.Customer;
import com.example.demo.businessrules.entities.exceptions.BusinessException;
import com.example.demo.businessrules.entities.exceptions.Error;
import com.example.demo.businessrules.usecases.steps.FindCustomerByDocument;
import com.example.demo.businessrules.usecases.steps.SaveCustomer;

import java.util.Arrays;

public class CreateCustomer {

    FindCustomerByDocument findCustomerByDocument;
    SaveCustomer saveCustomer;

    public CreateCustomer(FindCustomerByDocument findCustomerByDocument,
                          SaveCustomer saveCustomer) {
        this.findCustomerByDocument = findCustomerByDocument;
        this.saveCustomer = saveCustomer;
    }

    public Customer execute(Customer customer) {
        if (!customer.isValid()) {
            throw new BusinessException(customer.getErrors());
        }

        if(findCustomerByDocument.execute(customer.getDocument()).isPresent()) {
            throw new BusinessException(Arrays.asList(new Error("customer already exists")));
        };

        return saveCustomer.execute(customer);
    }
}
