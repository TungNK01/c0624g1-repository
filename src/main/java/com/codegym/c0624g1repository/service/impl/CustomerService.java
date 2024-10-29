package com.codegym.c0624g1repository.service.impl;

import com.codegym.c0624g1repository.exception.DuplicateEmailException;
import com.codegym.c0624g1repository.model.Customer;
import com.codegym.c0624g1repository.repository.ICustomerRepository;
import com.codegym.c0624g1repository.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Iterable<Customer> findAll(){
        return iCustomerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id){
        Optional<Customer> customerOptional = iCustomerRepository.findById(id);
        return customerOptional;
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

    @Override
    public Iterable<Customer> findAllCustomer(){
        return  iCustomerRepository.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) throws Exception {
        Optional<Customer> customerOptional = iCustomerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            throw new Exception(" customer not found!");
        }
        return customerOptional;
    }

    @Override
    public void saveCustomer(Customer customer) throws DuplicateEmailException {
        try {
            iCustomerRepository.save(customer);
        } catch (Exception e) {
            throw new DuplicateEmailException();
        }
        //iCustomerRepository.save(customer);
    }
}
