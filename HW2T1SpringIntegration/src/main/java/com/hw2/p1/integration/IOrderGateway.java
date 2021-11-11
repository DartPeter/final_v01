package com.hw2.p1.integration;

import com.hw2.p1.model.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "orderGateway", defaultRequestChannel = "orderGWDefaultRequestChannel")
public interface IOrderGateway {

    @Gateway
    void processOrderRequest(Message<Order> order);
}
