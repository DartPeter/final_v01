package com.mongodb.embedded.service;

import com.mongodb.embedded.entity.Account;
import com.mongodb.embedded.entity.Address;
import com.mongodb.embedded.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void testGet() {
        Long id = 1L;
        customerService.createCustomer(Customer.builder().id(id).firstName("fn1").lastName("ln1").build());
        Customer customer = customerService.getCustomerById(id).get();
        assertEquals(id, customer.getId());
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        customerService.createCustomer(Customer.builder().id(id).firstName("fn1").lastName("ln1").build());
        Customer customer = customerService.getCustomerById(id).get();
        String newFirstName = "fn1updated";
        String newLastName = "ln1updated";
        customer.setFirstName(newFirstName);
        customer.setLastName(newLastName);
        customerService.updateCustomer(customer);
        customer = customerService.getCustomerById(id).get();
        assertEquals(newFirstName, customer.getFirstName());
        assertEquals(newLastName, customer.getLastName());
    }

    @Test
    public void testGetByFirstNameAndLastName() {
        customerService.createCustomer(Customer.builder().id(1L).firstName("fn1").lastName("ln1").build());
        customerService.createCustomer(Customer.builder().id(2L).firstName("fn1").lastName("ln1").build());
        assertEquals(2, customerService.getCustomerByFirstNameAndLastName("fn1", "ln1").size());
    }

    @Test
    public void testGetWithAddresses() {
        customerService.createCustomer(Customer.builder().id(1L).firstName("fn1").lastName("ln1").
                addresses(
                        List.of(
                                Address.builder().line1("line1").line2("line2").countryCode("cc1").build(),
                                Address.builder().line1("line3").line2("line4").countryCode("cc2").build()
                                )).build());
        assertEquals(2, customerService.getCustomerById(1L).get().getAddresses().size());
    }

    @Test
    public void testGetByAddress() {
        Address address1 = Address.builder().line1("l11").line2("l21").countryCode("cc1").build();
        Address address2 = Address.builder().line1("l12").line2("l22").countryCode("cc2").build();
        Address address3 = Address.builder().line1("l13").line2("l23").countryCode("cc3").build();
        Customer customer1 = Customer.builder().id(1L).addresses(List.of(address1, address2)).build();
        Customer customer2 = Customer.builder().id(2L).addresses(List.of(address2, address3)).build();
        Customer customer3 = Customer.builder().id(3L).addresses(List.of(address3, address1)).build();
        customerService.createCustomer(customer1);
        customerService.createCustomer(customer2);
        customerService.createCustomer(customer3);
        assertEquals(2, customerService.getCustomerByAddress(address1).size());
    }

    @Test
    public void testGetByCardNumber() {
        Account account1 = Account.builder().nameOnAccount("na1").cardNumber("cn1")
                .expirationDate(LocalDate.of(2000, 2, 2)).build();
        Account account2 = Account.builder().nameOnAccount("na1").cardNumber("cn2")
                .expirationDate(LocalDate.of(2000, 2, 2)).build();
        Customer customer1 = Customer.builder().id(1L).accounts(List.of(account1)).build();
        Customer customer2 = Customer.builder().id(2L).accounts(List.of(account2)).build();
        Customer customer3 = Customer.builder().id(3L).accounts(List.of(account1, account2)).build();
        customerService.createCustomer(customer1);
        customerService.createCustomer(customer2);
        customerService.createCustomer(customer3);
        assertEquals(2, customerService.getCustomerByCardNumber("cn1").size());
    }

    @Test
    public void testGetUsersWithExpiredCards() {
        Account account1 = Account.builder().nameOnAccount("na1").cardNumber("cn1")
                .expirationDate(LocalDate.of(2000, 2, 2)).build();
        Account account2 = Account.builder().nameOnAccount("na1").cardNumber("cn2")
                .expirationDate(LocalDate.of(2001, 2, 2)).build();
        Account account3 = Account.builder().nameOnAccount("na1").cardNumber("cn2")
                .expirationDate(LocalDate.of(2002, 2, 2)).build();
        Customer customer1 = Customer.builder().id(1L).accounts(List.of(account1, account2)).build();
        Customer customer2 = Customer.builder().id(2L).accounts(List.of(account2, account3)).build();
        Customer customer3 = Customer.builder().id(3L).accounts(List.of(account1, account3)).build();
        customerService.createCustomer(customer1);
        customerService.createCustomer(customer2);
        customerService.createCustomer(customer3);
        assertEquals(2, customerService.getCustomerWithExpiredCard(LocalDate.of(2000, 3, 3)).size());
    }


}