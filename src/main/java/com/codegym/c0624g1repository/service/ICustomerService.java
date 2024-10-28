package com.codegym.c0624g1repository.service;

import com.codegym.c0624g1repository.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer>{
    List<Customer> findTop3ByFirstName(String firstName);

    List<Customer> findCustomerByLastName(String _lastName);

    Iterable<Customer> findAllCustomer() throws Exception ;
    Optional<Customer> findCustomerById(Long id) throws Exception ;
}
