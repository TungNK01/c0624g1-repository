package com.codegym.c0624g1repository.service.impl;

import com.codegym.c0624g1repository.model.Customer;
import com.codegym.c0624g1repository.repository.ICustomerRepository;
import com.codegym.c0624g1repository.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iCustomerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findTop3ByFirstName(String firstName) {
        return iCustomerRepository.findTop3ByFirstName(firstName);
    }

    @Override
    public List<Customer> findCustomerByLastName(String _lastName) {
        return iCustomerRepository.findCustomerByLastName(_lastName);
    }
}
