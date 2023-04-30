package vn.iotstar.model;

import java.util.List;

import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;

public class WhislistModel {
	private User user;
	String message;
	List<Product> list;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	
}
