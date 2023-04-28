package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Product;
import vn.iotstar.entity.Review;
import vn.iotstar.entity.User;

public interface ReviewRepository extends MongoRepository<Review, String>{
	List<Review> findByProduct(Product product);

	List<Review> findByUser(User user);

}
