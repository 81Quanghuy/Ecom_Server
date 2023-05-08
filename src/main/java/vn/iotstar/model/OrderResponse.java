package vn.iotstar.model;

import java.util.List;

import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderItem;

public class OrderResponse {
	
	private String message;
	
	private String userId;
	
	private List<OrderItem> item;
	
	private Order order;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<OrderItem> getItem() {
		return item;
	}

	public void setItem(List<OrderItem> item) {
		this.item = item;
	}
	

}
