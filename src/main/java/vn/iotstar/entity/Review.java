package vn.iotstar.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Review")
public class Review {

	@Id
	private String id;
	@DBRef
	private User user;
	@DBRef
	private Product product;
	private String content;
	private Integer rating;
	private String createat;
	private String updateat;
	private String productid;
	private String listimage;
	
	
	public String getListimage() {
		return listimage;
	}
	public void setListimage(String listimage) {
		this.listimage = listimage;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
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
