package com.example.demo;
public class lunchOrderItem implements ItemCommand {

	private OrderManagment orderManager;

	private OrderItem orderItem;

	private OrderManagment orderManagment;

	public abstract void submitOrderToOrderManagment();

	public void lunchOrderItem(String Itemname, OrderManagment orderMananger) {

	}

}
