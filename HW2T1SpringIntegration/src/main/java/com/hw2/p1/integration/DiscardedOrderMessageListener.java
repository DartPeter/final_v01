package com.hw2.p1.integration;

import com.hw2.p1.model.Order;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class DiscardedOrderMessageListener {

    @ServiceActivator(inputChannel = "orderFilterDiscardChannel")
    public void handleDiscardedOrder(Order order) {
        LoggerFactory.getLogger(DiscardedOrderMessageListener.class).info("Discard " + order);
    }
}
