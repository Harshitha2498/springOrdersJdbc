package com.app.model;

import java.util.Date;
import java.util.List;

/**
 * @author Harshitha D
 * @Created: may 28, 2024
 * @File : Orders.java
 * @Description :
 */
public class Orders {
	private int orderId;
	private Date orderDate;
	private List<OrderItems> OrderItems;
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", OrderItems=" + OrderItems + "]";
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public List<OrderItems> getOrderItems() {
		return OrderItems;
	}
	public void setOrderItems(List<OrderItems> OrderItems) {
		this.OrderItems = OrderItems;
	}
}
