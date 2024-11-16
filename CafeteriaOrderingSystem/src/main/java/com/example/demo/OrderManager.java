package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private int orderCounter; // Keeps track of Order IDs
    private Map<Integer, Order> orders; // Stores orders with their Order ID as the key

    public OrderManager() {
        this.orderCounter = 1000; // Start Order IDs at 1000
        this.orders = new HashMap<>();
    }

    /**
     * Places an order and generates an Order ID.
     *
     * @param customerID The alphanumeric Customer ID associated with the order.
     * @param items      The list of MenuItem objects included in the order.
     * @return The generated numeric Order ID.
     */
    public int placeOrder(String customerID, List<MenuItem> items) {
		if (items == null || items.isEmpty()) {
			throw new IllegalArgumentException("Order must contain at least one item.");
		}
	
		// Calculate total cost using mapToDouble
		double totalCost = items.stream()
								.mapToDouble(MenuItem::getPrice) // Correctly fetch price
								.sum();
	
		// Generate a new Order ID
		int orderID = generateOrderID();
	
		// Create and store the order
		Order order = new Order(orderID, customerID, items, totalCost);
		orders.put(orderID, order);
	
		// Print order details (for debugging/logging)
		System.out.println("Order placed successfully!");
		items.forEach(item -> System.out.println(item.getName() + " - $" + item.getPrice())); // Correctly fetch name and price
	
		return orderID;
	}
	
	

    /**
     * Retrieves the status of an order by its Order ID.
     *
     * @param orderID The Order ID.
     * @return The order status message.
     */
    public String getOrderStatus(int orderID) {
        Order order = orders.get(orderID);
        if (order == null) {
            return "Order ID " + orderID + " not found.";
        }
        return "Order ID " + orderID + " is confirmed. Total Cost: $" + order.getTotalCost();
    }

    /**
     * Generates a unique numeric Order ID.
     *
     * @return The new Order ID.
     */
    private int generateOrderID() {
        return orderCounter++;
    }

    // Optional: Retrieve the full order details by Order ID
    public Order getOrderDetails(int orderID) {
        return orders.get(orderID);
    }
}
