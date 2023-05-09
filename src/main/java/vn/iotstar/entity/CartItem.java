package vn.iotstar.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CartItem")
public class CartItem {
	@Id
	private String id;
	@DBRef
	private Cart cart;
	Product product;
	private Integer count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
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
	
}
