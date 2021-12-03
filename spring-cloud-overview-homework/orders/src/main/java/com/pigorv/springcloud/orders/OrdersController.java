package com.pigorv.springcloud.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping
@RestController
public class OrdersController {
    private List<Order> orderList = new ArrayList<>();

    @Autowired
    UserClient userClient;

    @Autowired
    ProductClient productClient;

    @Autowired
    NotificationClient notificationClient;

    @GetMapping
    public String health() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        userClient.getUser(order.getUserName());
        productClient.removeOneProduct(order.getProduct());
        notificationClient.createNewNotification(order.getUserName());

        orderList.add(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userName}")
    public List<String> getProductsForUser(@PathVariable String userName) {
        return orderList.stream()
                .filter(order -> userName.equals(order.getUserName()))
                .map(Order::getProduct)
                .collect(toList());
    }

    @Bean
    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
