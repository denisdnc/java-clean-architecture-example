package com.example.demo.interfaceadapters;

import com.example.demo.businessrules.entities.Customer;
import com.example.demo.interfaceadapters.vo.CustomerVO;

public class CustomerConverter {

    public CustomerVO convertToVO(Customer customer) {
        return new CustomerVO(customer.getNome(),customer.getDocumento());
    }

    public Customer convertFromVO(CustomerVO customerVO) {
        return new Customer(customerVO.getName(), customerVO.getDocument());
    }
}
