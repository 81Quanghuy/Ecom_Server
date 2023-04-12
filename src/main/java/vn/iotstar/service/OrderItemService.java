package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.StatusOrder;
import vn.iotstar.repository.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	OrderItemRepository orderItemRepo;

	public <S extends OrderItem> S save(S entity) {
		return orderItemRepo.save(entity);
	}

	@SuppressWarnings("null")
	public List<OrderItem> getListHuy(Order order){
		List<OrderItem> listHuy = null ;
		List<OrderItem> list = orderItemRepo.findByOrder(order);
		for (OrderItem orderItem : list) {
			if(orderItem.getOrder().getStatusOrder()==(StatusOrder.HUY)) {
				listHuy.add(orderItem);
			}
		}
		
		return listHuy;
	}
	
	@SuppressWarnings("null")
	public List<OrderItem> getListDangGiao(Order order){
		List<OrderItem> listHuy = null ;
		List<OrderItem> list = orderItemRepo.findByOrder(order);
		for (OrderItem orderItem : list) {
			if(orderItem.getOrder().getStatusOrder()==(StatusOrder.DANGGIAO)) {
				listHuy.add(orderItem);
			}
		}
		return listHuy;
	}
	
	@SuppressWarnings("null")
	public List<OrderItem> getListDaGiao(Order order){
		List<OrderItem> listHuy = null ;
		List<OrderItem> list = orderItemRepo.findByOrder(order);
		for (OrderItem orderItem : list) {
			if(orderItem.getOrder().getStatusOrder()==StatusOrder.DAGIAO) {
				listHuy.add(orderItem);
			}
		}
		return listHuy;
	}
	
	@SuppressWarnings("null")
	public List<OrderItem> getListChoXacNhan(Order order){
		List<OrderItem> listHuy = null ;
		List<OrderItem> list = orderItemRepo.findByOrder(order);
		for (OrderItem orderItem : list) {
			if(orderItem.getOrder().getStatusOrder()==(StatusOrder.CHOXACNHAN)) {
				listHuy.add(orderItem);
			}
		}
		return listHuy;
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
