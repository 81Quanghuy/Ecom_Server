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
	
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public CartItem(String id, Cart cart, Product product, Integer count) {
		super();
		this.id = id;
		this.cart = cart;
		this.product = product;
		this.count = count;
	}
	public CartItem() {
		super();
	}


	
}
