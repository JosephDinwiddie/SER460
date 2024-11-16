package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Customer {

    private int customerID;
    private String name;
    private String contactDetails;
    private Order currentOrder;
    private static Map<String, Order> orderHistory = new HashMap<>();

    public Customer(int customerID, String name, String contactDetails) {
        this.customerID = customerID;
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public void selectMealType(Menu menu, String mealType) {
        System.out.println("Available " + mealType + " items:");
        List<MenuItem> items = menu.filterByMealType(mealType);
        for (MenuItem item : items) {
            System.out.println(item);
        }
    }

    public void placeOrder(List<MenuItem> selectedItems) {
        if (selectedItems.isEmpty()) {
            System.out.println("No items selected. Please add items to your order.");
            return;
        }

        String orderNumber = UUID.randomUUID().toString();
        currentOrder = new Order(orderNumber, customerID, selectedItems, "Pending");
        orderHistory.put(orderNumber, currentOrder);

        System.out.println("Order placed successfully! Order Number: " + orderNumber);
    }

    public String checkOrderStatus(String orderNumber) {
        Order order = orderHistory.get(orderNumber);
        if (order == null) {
            return "Order not found.";
        }
        return "Order Status for " + orderNumber + ": " + order.getStatus();
    }

    // Getters and setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
}

class Order {
    private String orderNumber;
    private int customerID;
    private List<MenuItem> items;
    private String status;

    public Order(String orderNumber, int customerID, List<MenuItem> items, String status) {
        this.orderNumber = orderNumber;
        this.customerID = customerID;
        this.items = new ArrayList<>(items);
        this.status = status;
    }

    // Getters and setters
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber='" + orderNumber + '\'' +
                ", customerID=" + customerID +
                ", items=" + items +
                ", status='" + status + '\'' +
                '}';
    }
}
