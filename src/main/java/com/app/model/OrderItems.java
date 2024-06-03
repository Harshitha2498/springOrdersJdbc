package com.app.model;
/**
 * @author Harshitha D
 * @Created: may 28, 2024
 * @File : OrderItems.java
 * @Description :
 */
public class OrderItems {
	private int id;
	private Orders orders;
	private Items items;
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Order_Item [id=" + id + ", orders=" + orders + ", items=" + items + ", quantity=" + quantity + "]";
	}
}
