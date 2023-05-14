package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.User;
import vn.iotstar.entity.Wishlist;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {
	
	List<Wishlist> findByUser(User user);
	
}
