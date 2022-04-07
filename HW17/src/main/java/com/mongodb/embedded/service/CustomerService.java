package com.mongodb.embedded.service;

import com.mongodb.embedded.entity.Address;
import com.mongodb.embedded.entity.Customer;
import com.mongodb.embedded.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getCustomerByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Customer> getCustomerByAddress(Address address) {
    	return customerRepository.findByAddressesContaining(address);
    }

    public List<Customer> getCustomerByCardNumber(String cardNumber) {
        return customerRepository.findByAccountsCardNumber(cardNumber);
    }

    public List<Customer> getCustomerWithExpiredCard(LocalDate now) {
        return customerRepository.findByAccountsExpirationDateBefore(now);
    }

}
