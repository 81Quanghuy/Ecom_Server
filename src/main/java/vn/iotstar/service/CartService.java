package vn.iotstar.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.User;
import vn.iotstar.repository.CartRepository;
@Service
public class CartService {

	@Autowired
	private CartRepository cartRepo;

	public <S extends Cart> S save(S entity) {
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return cartRepo.save(entity);
	}

	public <S extends Cart> List<S> saveAll(Iterable<S> entities) {
		return cartRepo.saveAll(entities);
	}

	public <S extends Cart> S insert(S entity) {
		return cartRepo.insert(entity);
	}

	public <S extends Cart> List<S> insert(Iterable<S> entities) {
		return cartRepo.insert(entities);
	}

	public List<Cart> findAll() {
		return cartRepo.findAll();
	}

	public Cart findById(String id) {
		return cartRepo.findById(id).get();
	}

	public long count() {
		return cartRepo.count();
	}

	public void deleteById(String id) {
		cartRepo.deleteById(id);
	}

	public void delete(Cart entity) {
		cartRepo.delete(entity);
	}

	public void deleteAll(Iterable<? extends Cart> entities) {
		cartRepo.deleteAll(entities);
	}
	public Cart updateCart(Cart cartRequest){
        //get the existing document from DB
        // populate new value from request to existing object/entity/document
		Cart existingCart = cartRepo.findById(cartRequest.getId()).get();
		existingCart.setStore(cartRequest.getStore());
		existingCart.setUser(cartRequest.getUser());
		existingCart.setUpdateat(cartRequest.getUpdateat());
        return cartRepo.save(existingCart);
    }

	public Cart getCartByUser(User user) {
		// TODO Auto-generated method stub
		return cartRepo.getCartByUser(user);
	}
}
