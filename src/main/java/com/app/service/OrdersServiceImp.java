package com.app.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.app.model.Items;
import com.app.model.OrderItems;
import com.app.model.Orders;

@Service("ordersService")
public class OrdersServiceImp implements OrdersService {
	@Autowired
	private JdbcTemplate jdbcconfig;

	@Autowired
	public OrdersServiceImp(JdbcTemplate jdbcconfig) {
		this.jdbcconfig = jdbcconfig;
	}

	Orders orders;

	public List<Orders> listAllOrders() {
		return jdbcconfig.query("select * from orders", new RowMapper<Orders>() {
			public Orders mapRow(ResultSet rs, int row) throws SQLException {
				Orders orders = new Orders();
				orders.setOrderId(rs.getInt(1));
				orders.setOrderDate(rs.getDate(2));
				return orders;
			}
		});
	}

	@Override
	public List<OrderItems> getOrderItem(int id) {
		String sql = "select oi.id,i.item_id,i.item_name,i.item_price,oi.quantity from order_items oi left join items i on i.item_id=oi.item_id where oi.order_id="
				+ id + "";
		return jdbcconfig.query(sql, new RowMapper<OrderItems>() {
			public OrderItems mapRow(ResultSet rs, int row) throws SQLException {
				OrderItems orderitems = new OrderItems();
				Items items = new Items();
				Orders order = new Orders();
				orderitems.setId(rs.getInt(1));
				items.setItemId(rs.getInt(2));
				items.setItemName(rs.getString(3));
				items.setItemPrice(rs.getDouble(4));
				orderitems.setItems(items);
				order.setOrderId(id);
				orderitems.setOrders(order);
				orderitems.setQuantity(rs.getInt(5));
				return orderitems;
			}
		});
	}

	public List<Items> getAllItems() {
		String sql = "SELECT * FROM items";
		System.out.println("hellooo");
		return jdbcconfig.query(sql, new RowMapper<Items>() {
			@Override
			public Items mapRow(ResultSet rs, int rowNum) throws SQLException {
				Items item = new Items();
				item.setItemId(rs.getInt("item_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemPrice(rs.getDouble("item_price"));
				return item;
			}
		});
	}

	public int createNewOrder() {
		String sql = "Insert into Orders(order_date) values(?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcconfig.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, Date.valueOf(LocalDate.now()));
			return ps;
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Orders getOrderId(int id) {
		String sql = "select * from orders where order_id=(?)";
		return jdbcconfig.queryForObject(sql, new Object[] { id }, new RowMapper<Orders>() {
			@Override
			public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
				Orders order = new Orders();
				order.setOrderId(rs.getInt("order_id"));
				return order;
			}
		});
	}

	@Override
	public void delete(int orderId) {
		String deleteOrderItemsSql = "DELETE FROM order_items WHERE order_id = ?";
		String deleteOrderSql = "DELETE FROM orders WHERE order_id = ?";
		jdbcconfig.update(deleteOrderItemsSql, orderId);
		jdbcconfig.update(deleteOrderSql, orderId);
	}
	@Override
	public void deleteOrderItem(int id) {
		String deleteOrderItemsSql = "DELETE FROM order_items WHERE id = ?";
		jdbcconfig.update(deleteOrderItemsSql, id);
	}
	@Override
	public int save(int orderId, int itemId, int quantity) {
		String sql = "insert into order_Items(order_id,item_id,quantity) values(?,?,?)";
		return jdbcconfig.update(sql, orderId, itemId, quantity);
	}

	@Override
	public List<OrderItems> getOrderItemByid(int id) {
		String sql = "select  oi.id,oi.order_id,i.item_id,i.item_name,i.item_price,oi.quantity from order_items oi left join items i on i.item_id=oi.item_id where oi.id="
				+ id + "";
		return jdbcconfig.query(sql, new RowMapper<OrderItems>() {
			public OrderItems mapRow(ResultSet rs, int row) throws SQLException {
				OrderItems orderitems = new OrderItems();
				Items items = new Items();
				Orders order = new Orders();
				orderitems.setId(rs.getInt(1));
				order.setOrderId(rs.getInt(2));
				items.setItemId(rs.getInt(3));
				items.setItemName(rs.getString(4));
				items.setItemPrice(rs.getDouble(5));
				orderitems.setItems(items);
				orderitems.setOrders(order);
				orderitems.setQuantity(rs.getInt(6));
				return orderitems;
			}
		});
	}

	@Override
	public void editOrderItems(int orderItemId, int itemId, int quantity) {
		String sql = "update order_items set item_id=?,quantity=? where id=?";
		System.out.println(sql);
		jdbcconfig.update(sql, itemId, quantity, orderItemId);
	}
	@SuppressWarnings("deprecation")
	@Override
	public int fetchOrderIdById(int orderItemId) {
		String sql = "select oi.order_id from order_items oi where oi.id=?";
		return jdbcconfig.queryForObject(sql,new Object[] {orderItemId}, Integer.class);
	}

	
}
