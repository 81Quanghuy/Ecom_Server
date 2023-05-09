package vn.iotstar.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Order")
public class Order {
	
	@Id
	private String id;
	private User user;
	private String address;
	private String phone;
	private StatusOrder statusOrder; // Trang thai don hang 0.Cần xác nhận 1.Bị hủy 2. Đang giao 3. Đã giao
	private Float price;
	private Date createat;
	private Date updateat;
	
	@DBRef
	List<OrderItem> orderitem;
	
	private Boolean isactive;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
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
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	public StatusOrder getStatusOrder() {
		return statusOrder;
	}
	public void setStatusOrder(StatusOrder statusOrder) {
		this.statusOrder = statusOrder;
	}

}
