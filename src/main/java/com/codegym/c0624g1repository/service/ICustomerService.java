package com.codegym.c0624g1repository.service;

import com.codegym.c0624g1repository.model.Customer;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer>{
    List<Customer> findTop3ByFirstName(String firstName);
}
