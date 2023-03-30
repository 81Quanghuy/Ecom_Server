package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.CartItem;

public interface CartItemRepository extends MongoRepository<CartItem, String>{

}
