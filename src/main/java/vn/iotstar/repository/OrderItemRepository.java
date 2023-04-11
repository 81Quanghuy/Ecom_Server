package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderItem;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
	List<OrderItem> findByOrder(Order order);
}
