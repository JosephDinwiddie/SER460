package com.example.demo;
public class OrderItem {

	/*************VARIABLES******************/
	private int orderItemID;
	private int quantity;

	private MenuItem itemDetails;

	private String orderType;
	public int getOrderItemID() {
		return orderItemID;
	}
	/*************CONSTRUCTOR******************/



	/*************FUNCTIONS******************/
	public void setOrderItemID(int orderItemID) {
		this.orderItemID = orderItemID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public MenuItem getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(MenuItem itemDetails) {
		this.itemDetails = itemDetails;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public float calculateSubtotal() {
		return 0;
	}

}
