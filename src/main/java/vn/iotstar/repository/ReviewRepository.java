package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Product;
import vn.iotstar.entity.Review;

public interface ReviewRepository extends MongoRepository<Review, String>{
	List<Review> findByProduct(Product product);

}
