package com.example.demo.interfaceadapters.dataaccess;

import com.example.demo.businessrules.entities.Customer;
import com.example.demo.businessrules.usecases.steps.FindCustomerByDocument;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindCustomerByDocumentMongoImpl implements FindCustomerByDocument {

    private CustomerRepository customerRepository;

    public FindCustomerByDocumentMongoImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Optional<Customer> execute(String document) {
        return Optional.ofNullable(customerRepository.findByDocumento(document));
    }
}
