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
	private Date createat;
	private Date updateat;
	
	@DBRef
	private User user;
	
	public Delivery() {
		super();
	}
	public Delivery(String id, String address, Date createat, Date updateat) {
		super();
		this.id = id;
		this.address = address;
		this.createat = createat;
		this.updateat = updateat;
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
	public Date getCreateat() {
		return createat;
	}
	public void setCreateat(Date createat) {
		this.createat = createat;
	}
	public Date getUpdateat() {
		return updateat;
	}
	public void setUpdateat(Date updateat) {
		this.updateat = updateat;
	}
	
}
