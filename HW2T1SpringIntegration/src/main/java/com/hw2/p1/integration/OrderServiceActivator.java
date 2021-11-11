package com.hw2.p1.integration;

import com.hw2.p1.model.Order;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class OrderServiceActivator {

    @ServiceActivator(inputChannel = "orderFilterOutputChannel")
    public void getOrder (Order order) {
        LoggerFactory.getLogger(OrderServiceActivator.class).info("Order succesfully received " + order);
    }
}
