package com.example.demo;
import java.util.Collection;

public class Order implements updateOrderIterface {

	private int orderID;

	private String orderStatus;

	private Customer customerDetails;

	private DateTime orderTime;

	private float totalAmount;

	private int quantity;

	private MenuItem itemDetails;

	private List<ItemCommand> listOfItems;

	private Customer customer;

	private UserInterface userInterface;

	private Collection<OrderItem> orderItem;

	private OrderManagment orderManagment;

	private Collection<ItemCommand> itemCommand;

	private OrderManagment orderManagment;

	public void calculateTotal() {

	}

	public void submitOrderItems(String ordersToStart) {

	}

	public void addOrderItem(ItemCommand command) {

	}


	/**
	 * @see updateOrderIterface#updateOrderStatus(String)
	 */
	public void updateOrderStatus(String String) {

	}

}
