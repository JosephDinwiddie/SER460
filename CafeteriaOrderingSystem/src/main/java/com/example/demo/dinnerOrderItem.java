
package com.example.demo;
public class dinnerOrderItem implements ItemCommand {

	private OrderManagment orderManager;

	private OrderItem orderItem;

	private OrderManagment orderManagment;

	public abstract void submitOrderToOrderManagment();

	public void dinnerOrderItem(String Itemname, OrderManagment orderMananger) {

	}

}
