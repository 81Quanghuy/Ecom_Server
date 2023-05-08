package vn.iotstar.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CartItem")
public class CartItem {
	@Id
	private String id;
	@DBRef
	Product product;
	private Integer count;
<<<<<<< HEAD
	private String createat;
	private String updateat;
	public String getId() {
=======

	public Integer getId() {
>>>>>>> 93b0be1535cae45a52eaa59e2a4ecedd8a6794b8
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

	public String getCreateat() {
		return createat;
	}
	public void setCreateat(String createat) {
		this.createat = createat;
	}
	public String getUpdateat() {
		return updateat;
	}
	public void setUpdateat(String updateat) {
		this.updateat = updateat;
	}
	
}
