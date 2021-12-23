package com.mongodb.embedded.service;

import com.mongodb.embedded.entity.Account;
import com.mongodb.embedded.entity.Address;
import com.mongodb.embedded.entity.Customer;
import com.mongodb.embedded.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Customer> customers = customerRepository.findAll();
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            for (Address address1 : customer.getAddresses()) {
                if(address1.equals(address)) {
                    result.add(customer);
                    break;
                }
            }
        }
        return result;
    }

    public List<Customer> getCustomerByCardNumber(String cardNumber) {
        List<Customer> customers = customerRepository.findAll();
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            for (Account account: customer.getAccounts()) {
                if(account.getCardNumber().equals(cardNumber)) {
                    result.add(customer);
                    break;
                }
            }
        }
        return result;
    }

    public List<Customer> getCustomerWithExpiredCard(LocalDate now) {
        List<Customer> customers = customerRepository.findAll();
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            for (Account account: customer.getAccounts()) {
                if(account.getExpirationDate().isBefore(now)) {
                    result.add(customer);
                    break;
                }
            }
        }
        return result;
    }

}
