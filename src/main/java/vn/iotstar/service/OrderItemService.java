package vn.iotstar.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.OrderItem;
import vn.iotstar.repository.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	OrderItemRepository orderItemRepo;

	public <S extends OrderItem> S save(S entity) {
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return orderItemRepo.save(entity);
	}

	public <S extends OrderItem> S insert(S entity) {
		return orderItemRepo.insert(entity);
	}

	public List<OrderItem> findAll() {
		return orderItemRepo.findAll();
	}

	public Optional<OrderItem> findById(String id) {
		return orderItemRepo.findById(id);
	}

	public boolean existsById(String id) {
		return orderItemRepo.existsById(id);
	}

	public long count() {
		return orderItemRepo.count();
	}

	public void deleteById(String id) {
		orderItemRepo.deleteById(id);
	}

	public void delete(OrderItem entity) {
		orderItemRepo.delete(entity);
	}

	public void deleteAll() {
		orderItemRepo.deleteAll();
	}
	
}
