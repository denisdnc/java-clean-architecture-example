package com.example.demo.businessrules.usecases.steps;

import com.example.demo.businessrules.entities.Customer;

import java.util.Optional;

@FunctionalInterface
public interface SaveCustomer {

    Customer execute(Customer customer);
}
