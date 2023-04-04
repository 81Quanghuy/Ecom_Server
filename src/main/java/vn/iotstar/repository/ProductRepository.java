package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
public interface ProductRepository extends MongoRepository<Product,String>{
	
	
	List<Product> findByBarcode(String barcode);
	
	List<Product> findByCategory(Category category);
}
