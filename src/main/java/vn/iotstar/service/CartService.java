package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.User;
import vn.iotstar.repository.CartRepository;

@Service
public class CartService  {
	@Autowired
	CartRepository repository;

	public List<Cart> findByUser(User user) {
		return repository.findByUser(user);
	}

	public <S extends Cart> S save(S entity) {
		return repository.save(entity);
	}

	public <S extends Cart> List<S> saveAll(Iterable<S> entities) {
		return repository.saveAll(entities);
	}

	public <S extends Cart> S insert(S entity) {
		return repository.insert(entity);
	}

	public List<Cart> findAll() {
		return repository.findAll();
	}

	public Optional<Cart> findById(Integer id) {
		return repository.findById(id);
	}

	public long count() {
		return repository.count();
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public void delete(Cart entity) {
		repository.delete(entity);
	}

	public void deleteAll() {
		repository.deleteAll();
	}
	


}
