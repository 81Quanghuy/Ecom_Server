package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Delivery;
import vn.iotstar.entity.User;
import vn.iotstar.repository.DeliveryRepository;

@Service
public class DeliveryService {

	@Autowired
	DeliveryRepository deliveryRepo;

	public <S extends Delivery> S save(S entity) {
		return deliveryRepo.save(entity);
	}

	public <S extends Delivery> S insert(S entity) {
		return deliveryRepo.insert(entity);
	}

	public List<Delivery> findAll() {
		return deliveryRepo.findAll();
	}

	public Optional<Delivery> findById(String id) {
		return deliveryRepo.findById(id);
	}

	public boolean existsById(String id) {
		return deliveryRepo.existsById(id);
	}

	public long count() {
		return deliveryRepo.count();
	}

	public void deleteById(String id) {
		deliveryRepo.deleteById(id);
	}

	public void delete(Delivery entity) {
		deliveryRepo.delete(entity);
	}

	public void deleteAll() {
		deliveryRepo.deleteAll();
	}

	public List<Delivery> findByUser(User user) {
		return deliveryRepo.findByUser(user);
	}
	
	
}
