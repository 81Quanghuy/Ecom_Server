package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
