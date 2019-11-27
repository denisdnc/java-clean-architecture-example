package com.example.demo.interfaceadapters.dataaccess;

import com.example.demo.businessrules.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByDocumento(String documento);
}
