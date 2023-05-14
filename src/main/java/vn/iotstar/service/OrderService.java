package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Order;
import vn.iotstar.entity.StatusOrder;
import vn.iotstar.entity.User;
import vn.iotstar.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;

	public List<Order> findByUpdateat(String updateat) {
		return orderRepo.findByUpdateat(updateat);
	}

	public <S extends Order> S save(S entity) {
		return orderRepo.save(entity);
	}

	public List<Order> findByStatusOrder(StatusOrder statusOrder) {
		return orderRepo.findByStatusOrder(statusOrder);
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
	
	//tìm đơn hàng theo user
	public List<Order> findByUser(User user) {
		return orderRepo.findByUser(user);
	}
	
	
}
