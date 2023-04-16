package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Product;
import vn.iotstar.entity.Review;
import vn.iotstar.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepo;

	public <S extends Review> S save(S entity) {
		return reviewRepo.save(entity);
	}

	public <S extends Review> S insert(S entity) {
		return reviewRepo.insert(entity);
	}

	public List<Review> findAll() {
		return reviewRepo.findAll();
	}

	public Optional<Review> findById(String id) {
		return reviewRepo.findById(id);
	}

	public long count() {
		return reviewRepo.count();
	}

	public void delete(Review entity) {
		reviewRepo.delete(entity);
	}

	public void deleteAll() {
		reviewRepo.deleteAll();
	}

	public List<Review> findByProduct(Product product) {
		return reviewRepo.findByProduct(product);
	}
	
	
	
}
