package com.app.service;

import java.util.List;
import com.app.model.Items;
import com.app.model.OrderItems;
import com.app.model.Orders;

public interface OrdersService {
	List<Orders> listAllOrders();
	List<OrderItems> getOrderItem(int id);
	List<Items> getAllItems();
	int createNewOrder();
	Orders getOrderId(int id);
	void delete(int id);
	int save(int orderId,int itemId, int quantity);
	List<OrderItems> getOrderItemByid(int id);
	void editOrderItems(int orderItemId, int itemId, int quantity);
	int fetchOrderIdById(int orderItemId);
	void deleteOrderItem(int id);
}
