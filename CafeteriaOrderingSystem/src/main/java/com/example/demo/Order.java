package com.example.demo;

import java.util.List;

public class Order {
    private int orderID;
    private String customerID;
    private List<MenuItem> items;
    private double totalCost;

    public Order(int orderID, String customerID, List<MenuItem> items, double totalCost) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.items = items;
        this.totalCost = totalCost;
    }

    // Getters and setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderID).append("\n");
        sb.append("Customer ID: ").append(customerID).append("\n");
        sb.append("Items:\n");
        for (MenuItem item : items) {
            sb.append("  - ").append(item.getName()).append(": $").append(item.getPrice()).append("\n");
        }
        sb.append("Total Cost: $").append(totalCost);
        return sb.toString();
    }
}
