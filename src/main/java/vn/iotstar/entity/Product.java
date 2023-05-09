package vn.iotstar.entity;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
public class Product {

	@Id
	private String id;
	private String name;
	private String desciption;
	private Integer price;
	private Integer promotionaprice;
	private Integer quantity;
	private Integer sold;
	private Boolean isselling;
	private String listimage;
	@DBRef
	private Category category;
	private Double rating;
	private String createat;
	private String updateat;
	
	@NotBlank
	private String barcode;
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getPromotionaprice() {
		return promotionaprice;
	}
	public void setPromotionaprice(Integer promotionaprice) {
		this.promotionaprice = promotionaprice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getSold() {
		return sold;
	}
	public void setSold(Integer sold) {
		this.sold = sold;
	}
	public Boolean getIsselling() {
		return isselling;
	}
	public void setIsselling(Boolean isselling) {
		this.isselling = isselling;
	}
	public String getListimage() {
		return listimage;
	}
	public void setListimage(String listimage) {
		this.listimage = listimage;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
		

}
