package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.Product;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, Integer> {

	List<CartItem> findByCart(Cart cart);

	List<CartItem> findByProduct(Product product);
	
}