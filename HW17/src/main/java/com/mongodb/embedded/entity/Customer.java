package com.mongodb.embedded.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class Customer {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    List<Address> addresses;
    List<Account> accounts;

}
