package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dashboard {

	private List<Order> activeOrders;

	private List<Order> completedOrders;

	private OrderManager orderManagment;

	/*To be added when implementing Cafeteria Manager functionality in Phase 3*/
	private CafeteriaOperator cafeteriaOperator;

	private static Dashboard instance;
	private Dashboard(){
		cafeteriaOperator = CafeteriaOperator.getInstance();
		orderManagment = OrderManager.getInstance();
	}
	public static Dashboard getInstance() {
		if (instance == null) {
			instance = new Dashboard();
		}
		return instance;
	}



	public void displayOrders() {

	}

	public List<Order> filterOrders(String criteria) {
		return null;
	}

	public void addMenuItem(String type, int itemID, String name, float price, boolean availability) {
		/*To be added when implementing Cafeteria Manager functionality in Phase 3*/
	}

	public List<String> getAllOrdersIDs(){
		List<String> listOfIDs = new ArrayList<>();

		// Check if orderManagment is null
		if (orderManagment == null) {
			listOfIDs.add("no orders");
			return listOfIDs;
		}

		// Get the order map
		Map<Integer, Order> orderMap = orderManagment.getOrders();

		// Iterate over the map using forEach
		orderMap.forEach((key, order) -> {
			listOfIDs.add(Integer.toString(order.getOrderID()));
		});

		return listOfIDs;
	}

	public void removeOrder(Order order) {
		OrderManager.getInstance().removeOrder(order);
	}

	public List<Order> getAllOrders(){
			List<Order> listOfOrders = new ArrayList<>();

			// Check if orderManagment is null
			if (orderManagment == null) {
				return listOfOrders;
			}

			// Get the order map
			Map<Integer, Order> orderMap = orderManagment.getOrders();

			// Iterate over the map using forEach
			orderMap.forEach((key, order) -> {
				listOfOrders.add(order);
			});

			return listOfOrders;

	}
}
