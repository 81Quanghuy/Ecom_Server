package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Delivery;

public interface DeliveryRepository extends MongoRepository<Delivery, String>{

}
