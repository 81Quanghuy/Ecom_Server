package vn.iotstar.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.CartItem;
import vn.iotstar.repository.CartItemRepository;

@Service
public class CartItemService {

	@Autowired
	CartItemRepository cartItemRepo;

	public <S extends CartItem> S save(S entity) {
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return cartItemRepo.save(entity);
	}

	public <S extends CartItem> S insert(S entity) {
		return cartItemRepo.insert(entity);
	}

	public List<CartItem> findAll() {
		return cartItemRepo.findAll();
	}

	public Optional<CartItem> findById(String id) {
		return cartItemRepo.findById(id);
	}

	public long count() {
		return cartItemRepo.count();
	}

	public void delete(CartItem entity) {
		cartItemRepo.delete(entity);
	}

	public void deleteAll() {
		cartItemRepo.deleteAll();
	}
	
}
