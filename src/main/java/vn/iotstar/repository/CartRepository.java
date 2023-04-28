package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.User;


public interface CartRepository extends MongoRepository<Cart, Integer> {

	List<Cart> findByUser(User user);

}
