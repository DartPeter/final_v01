package com.mongodb.embedded.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    String line1;
    String line2;
    String countryCode;
}
