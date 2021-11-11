package com.hw2.p1.integration;

import com.hw2.p1.model.Order;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;

@MessageEndpoint
public class OrderSplitter {

    @Splitter(inputChannel = "orderGWDefaultRequestChannel",
            outputChannel = "orderSplitterOutputChannel")
    public Order splitCargo(Message<Order> message) {
        return message.getPayload();
    }
}
