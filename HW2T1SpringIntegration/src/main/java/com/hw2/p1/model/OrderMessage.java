package com.hw2.p1.model;

public class OrderMessage {

    private final Order order;

    public OrderMessage(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "OrderMessage{" +
                "order=" + order +
                '}';
    }
}
