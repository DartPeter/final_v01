package com.mongodb.embedded.repository;

import com.mongodb.embedded.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, Long> {

    public List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    public List<Customer> findByAddresses(String addresses);
    public List<Customer> findByAccounts(String accounts);
}
