package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import vn.iotstar.entity.Product;
public interface ProductRepository extends MongoRepository<Product,String>{
	
	@Query("barcode: ?0")
	List<Product> findByBarcode(String barcode);
}
