package vn.iotstar.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Delivery")
public class Delivery {
	@Id
	private String id;
	private String address;
	
	@DBRef
	private User user;
	
	public Delivery() {
		super();
	}
	public Delivery(String id, String address) {
		super();
		this.id = id;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
}
