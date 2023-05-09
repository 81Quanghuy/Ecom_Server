package vn.iotstar.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Wishlist")
public class Wishlist {

	 	private String id;
	 	
	 	@DBRef
	    private User user;
	 	
	 	@DBRef
	    private List<Product> products;
	 	
	 	

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

		public List<Product> getProducts() {
	        return products;
	    }

	    public void setProducts(List<Product> products) {
	        this.products = products;
	    }
}
