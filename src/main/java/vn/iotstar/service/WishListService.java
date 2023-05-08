package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Wishlist;
import vn.iotstar.repository.WishlistRepository;

@Service
public class WishListService {
	@Autowired
	WishlistRepository repository;

	public <S extends Wishlist> S save(S entity) {
		return repository.save(entity);
	}

	public <S extends Wishlist> S insert(S entity) {
		return repository.insert(entity);
	}

	public Optional<Wishlist> findById(String id) {
		return repository.findById(id);
	}

	public void delete(Wishlist entity) {
		repository.delete(entity);
	}
	
	
	public Wishlist findByUserId(String userId) {
		return repository.findByUserId(userId);
	}

	public Wishlist getWishlistByUserId(String id) {
		List<Wishlist> list = repository.findAll();
		for(Wishlist wish : list) {
			if(wish.getUserId() == id)
				return wish;
		}
		return null;
	}

	
	

}
