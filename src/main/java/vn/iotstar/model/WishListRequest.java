package vn.iotstar.model;

import java.util.List;

import vn.iotstar.entity.Product;

public class WishListRequest {
	String message;
	List<Product> list;
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
	public WishListRequest(String message, List<Product> list) {
		super();
		this.message = message;
		this.list = list;
	}
	
	
}
