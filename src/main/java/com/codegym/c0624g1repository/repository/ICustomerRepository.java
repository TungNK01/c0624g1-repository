package com.codegym.c0624g1repository.repository;

import com.codegym.c0624g1repository.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select c from Customer c where c.firstName = ?1")
    List<Customer> findTop3ByFirstName(String firstName);

    @Query(value = "CALL get_customer_by_last_name(:_lastName);", nativeQuery = true)
    List<Customer> findCustomerByLastName(@Param("_lastName") String _lastName);

}
