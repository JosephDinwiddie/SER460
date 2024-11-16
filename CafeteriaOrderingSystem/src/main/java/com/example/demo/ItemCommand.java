package com.example.demo;
public interface ItemCommand {

	private Driver driver;

	private OrderManager orderManagment;

	private Order order;

	public abstract void submitOrderToOrderManagment();

}
