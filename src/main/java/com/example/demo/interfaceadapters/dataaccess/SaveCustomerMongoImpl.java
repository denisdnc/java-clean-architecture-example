package com.example.demo.interfaceadapters.dataaccess;

import com.example.demo.businessrules.entities.Customer;
import com.example.demo.businessrules.usecases.steps.SaveCustomer;
import org.springframework.stereotype.Component;

@Component
public class SaveCustomerMongoImpl implements SaveCustomer {

    private CustomerRepository customerRepository;

    public SaveCustomerMongoImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer execute(Customer customer) {
        return customerRepository.save(customer);
    }
}
