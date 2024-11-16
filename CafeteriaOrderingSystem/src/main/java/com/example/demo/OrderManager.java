package com.example.demo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public String placeOrder(int customerId, List<MenuItem> items) {
        String orderId = "ORD" + (orders.size() + 1);
        Order order = new Order(orderId, customerId, items, "Pending");
        orders.add(order);
        return orderId;
    }

    public String getOrderStatus(String orderId) {
        for (Order order : orders) {
            if (order.getOrderNumber().equals(orderId)) {
                return order.getStatus();
            }
        }
        return "Order not found";
    }
}