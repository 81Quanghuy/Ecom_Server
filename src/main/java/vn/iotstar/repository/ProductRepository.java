package vn.iotstar.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
public interface ProductRepository extends MongoRepository<Product,String>{
	
	
	List<Product> findByBarcode(String barcode);
	
	List<Product> findByCategory(Category category);
	
	Optional<Product> findById(String id);
	
	List<Product> findByIsselling(Boolean isselling);
	
	List<Product> findByQuantity(Integer quantity);
	
	List<Product> findTop3ByOrderBySoldDesc();
	
}
