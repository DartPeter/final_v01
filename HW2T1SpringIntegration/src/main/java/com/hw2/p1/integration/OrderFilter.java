package com.hw2.p1.integration;

import com.hw2.p1.model.Order;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class OrderFilter {

    @Filter(inputChannel = "orderSplitterOutputChannel", outputChannel = "orderFilterOutputChannel", discardChannel = "orderFilterDiscardChannel")
    public boolean filterIfOrderCncelled(Order order) {return order.getOrderState() != Order.OrderState.CANCELED;}
}
