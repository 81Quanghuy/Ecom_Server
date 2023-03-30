package vn.iotstar.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Order;
import vn.iotstar.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;

	public <S extends Order> S save(S entity) {
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return orderRepo.save(entity);
	}

	public <S extends Order> S insert(S entity) {
		return orderRepo.insert(entity);
	}

	public List<Order> findAll() {
		return orderRepo.findAll();
	}

	public Optional<Order> findById(String id) {
		return orderRepo.findById(id);
	}

	public boolean existsById(String id) {
		return orderRepo.existsById(id);
	}

	public long count() {
		return orderRepo.count();
	}

	public void deleteById(String id) {
		orderRepo.deleteById(id);
	}

	public void delete(Order entity) {
		orderRepo.delete(entity);
	}

	public void deleteAll() {
		orderRepo.deleteAll();
	}
	
}
