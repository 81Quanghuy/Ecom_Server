package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.OrderItem;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {

}
