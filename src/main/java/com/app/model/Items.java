package com.app.model;

/**
 * @Author : Harshitha D
 * @Created: may 28, 2024
 * @File : Items.java
 * @Description :
 */
public class Items {
	private int itemId;
	private String itemName;
	private double itemPrice;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	@Override
	public String toString() {
		return "Items [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + "]";
	}
}
