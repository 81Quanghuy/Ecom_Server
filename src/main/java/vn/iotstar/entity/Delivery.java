package vn.iotstar.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Delivery")
public class Delivery {
	@Id
	private String id;
	private String address;
	private boolean isHome;
	private boolean isDefault;
	
	@DBRef
	private User user;
	
	public Delivery() {
		super();
	}

	public Delivery(String id, String address, boolean isHome, boolean isDefault, User user) {
		super();
		this.id = id;
		this.address = address;
		this.isHome = isHome;
		this.isDefault = isDefault;
		this.user = user;
	}

	public boolean isHome() {
		return isHome;
	}

	public void setHome(boolean isHome) {
		this.isHome = isHome;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
