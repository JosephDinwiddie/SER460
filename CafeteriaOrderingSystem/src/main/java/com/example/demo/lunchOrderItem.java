package com.example.demo;
public abstract class lunchOrderItem implements ItemCommand {

	private OrderManager orderManager;

	private OrderItem orderItem;

	private OrderManager orderManagment;

	public abstract void submitOrderToOrderManagment();

	public void lunchOrderItem(String Itemname, OrderManager orderMananger) {

	}

}
