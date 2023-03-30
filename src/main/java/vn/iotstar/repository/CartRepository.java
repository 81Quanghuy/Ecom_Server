package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.User;

public interface CartRepository extends MongoRepository<Cart,String>  {

	@Query("{ user: ?0 }")
	Cart getCartByUser(User user);

}
