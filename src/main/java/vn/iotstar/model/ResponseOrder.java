package vn.iotstar.model;

import java.util.List;

import vn.iotstar.entity.Order;

public class ResponseOrder {
	
	String message;
	List<Order> order;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public ResponseOrder(String message, List<Order> order) {
		super();
		this.message = message;
		this.order = order;
	}

	
}
