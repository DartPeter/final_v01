package com.mongodb.embedded.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Account {
    String cardNumber;
    String nameOnAccount;
    LocalDate expirationDate;
}
