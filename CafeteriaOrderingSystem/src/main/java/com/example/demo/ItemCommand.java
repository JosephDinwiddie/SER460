package com.example.demo;
public interface ItemCommand {

	private Driver driver;

	private OrderManagment orderManagment;

	private Order order;

	public abstract void submitOrderToOrderManagment();

}
