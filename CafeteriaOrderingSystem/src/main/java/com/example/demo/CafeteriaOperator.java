package com.example.demo;


import java.util.ArrayList;
import java.util.List;


public class CafeteriaOperator {

	private static CafeteriaOperator instance;
	private int operatorID;

	private String name;

	private String role;

	private Dashboard dashboard;

	private CafeteriaOperator(){

	}
	public static CafeteriaOperator getInstance() {
		if (instance == null) {
			instance = new CafeteriaOperator();
		}
		return instance;
	}
	public List<Order> viewOrders() {
		return dashboard.getAllOrders();
	}

	public void processOrder(Order order) {
		if (order != null) {
            // Logic to process the order
            System.out.println("Order processed: " + order.getOrderID());
        } else {
			System.out.println("Order is null");
		}

	}

	public Dashboard getDashboard() {
		if(dashboard == null){
			dashboard = Dashboard.getInstance();
		}
		return dashboard;
	}

	public void updateOrderStatus() {

		System.out.println("Order status updated.");

    }

}
