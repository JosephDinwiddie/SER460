package com.example.demo;
public class beverageOrderItem implements ItemCommand {

	private OrderManagment orderManager;

	private OrderItem orderItem;

	private OrderManagment orderManagment;

	public abstract void submitOrderToOrderManagment();

	public void beverageOrderItem(String Itemname, OrderManagment orderMananger) {

	}

}
