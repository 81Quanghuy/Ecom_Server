package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Delivery;
import vn.iotstar.entity.User;

public interface DeliveryRepository extends MongoRepository<Delivery, String>{
	
	List<Delivery> findByUser(User user);

}
