
package com.example.demo;
public class dinnerOrderItem implements ItemCommand {

	private OrderManager orderManager;

	private OrderItem orderItem;

	private OrderManager orderManagment;

	public abstract void submitOrderToOrderManagment();

	public void dinnerOrderItem(String Itemname, OrderManager orderMananger) {

	}

}
