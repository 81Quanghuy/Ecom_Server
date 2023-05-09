package vn.iotstar.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.Product;
import vn.iotstar.repository.CartItemRepository;

@Service
public class CartItemService {
	
	@Autowired
	CartItemRepository cartItemRepository;

//	public List<CartItem> findByCart(Cart cart) {
//		return cartItemRepository.findByCart(cart);
//	}

	public List<CartItem> findByProduct(Product product) {
		return cartItemRepository.findByProduct(product);
	}
	public <S extends CartItem> S save(S entity) {
	
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return cartItemRepository.save(entity);
	}

	public <S extends CartItem> Optional<S> findOne(Example<S> example) {
		return cartItemRepository.findOne(example);
	}

	public List<CartItem> findAll(Sort sort) {
		return cartItemRepository.findAll(sort);
	}

	public <S extends CartItem> S insert(S entity) {
		return cartItemRepository.insert(entity);
	}

	public <S extends CartItem> List<S> insert(Iterable<S> entities) {
		return cartItemRepository.insert(entities);
	}

	public Optional<CartItem> findById(Integer id) {
		return cartItemRepository.findById(id);
	}

	public boolean existsById(Integer id) {
		return cartItemRepository.existsById(id);
	}

	public <S extends CartItem> boolean exists(Example<S> example) {
		return cartItemRepository.exists(example);
	}

	public long count() {
		return cartItemRepository.count();
	}

	public void deleteById(Integer id) {
		cartItemRepository.deleteById(id);
	}

	public void delete(CartItem entity) {
		cartItemRepository.delete(entity);
	}

	public void deleteAll() {
		cartItemRepository.deleteAll();
	}
	
}
