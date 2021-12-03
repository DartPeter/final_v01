package com.pigorv.springcloud.orders;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private Long quantity;
}
