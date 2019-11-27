package com.example.demo.interfaceadapters.controllers;

import com.example.demo.businessrules.entities.Customer;
import com.example.demo.businessrules.usecases.CreateCustomer;
import com.example.demo.interfaceadapters.CustomerConverter;
import com.example.demo.interfaceadapters.vo.CustomerVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CreateCustomer createCustomer;
    private CustomerConverter customerConverter;

    public CustomerController(CreateCustomer createCustomer, CustomerConverter customerConverter) {
        this.createCustomer = createCustomer;
        this.customerConverter = customerConverter;
    }

    @PostMapping(value = "/customer")
    public ResponseEntity createCustomer(@RequestBody CustomerVO customerVo) {

        Customer resultCustomer = customerConverter.convertFromVO(customerVo);

        CustomerVO result = customerConverter.convertToVO(createCustomer.execute(resultCustomer));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
