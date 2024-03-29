package com.pigorv.springcloud.orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "products", fallback = ProductClient.ProductClientImpl.class)
public interface ProductClient {

    @PutMapping("/{productName}")
    ProductDto removeOneProduct(@PathVariable String productName);

    @Component
    class ProductClientImpl implements ProductClient {
        @Override
        public ProductDto removeOneProduct(String productName) {
            ProductDto productDto = new ProductDto();
            productDto.setName("Something went wrong");
            productDto.setQuantity(-1L);
            return productDto;
        }
    }

}
