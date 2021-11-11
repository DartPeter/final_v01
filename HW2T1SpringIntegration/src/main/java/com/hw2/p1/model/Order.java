package com.hw2.p1.model;

public class Order {

    private int id;
    private OrderState orderState;

    public enum OrderState {
        CANCELED,
        WAITING_FOR_PAYMENT,
        PAYMENT_COMPLETED
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Order(int id, OrderState orderState) {
        this.id = id;
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderState=" + orderState +
                '}';
    }
}
