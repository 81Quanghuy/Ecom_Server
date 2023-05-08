package vn.iotstar.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cart")
public class Cart {
	@Id
	private String id;
	private User user;
//	private Date createat;
//	private Date updateat;
	
	@DBRef
	private List<CartItem> cartItems;
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
//	public Date getCreateat() {
//		return createat;
//	}
//	public void setCreateat(Date createat) {
//		this.createat = createat;
//	}
//	public Date getUpdateat() {
//		return updateat;
//	}
//	public void setUpdateat(Date updateat) {
//		this.updateat = updateat;
//	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
}
