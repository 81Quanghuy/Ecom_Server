package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Product;
import vn.iotstar.entity.Review;
import vn.iotstar.entity.User;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	ProductRepository productRepository;

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

	public List<Review> findByUser(User user) {
		return reviewRepo.findByUser(user);
	}
	
	 public double calculateAverageRatingByProduct(String productId) {
	        //Optional<Product> optionalProduct = productRepository.findById(productId);
		 Optional<Product> product = productRepository.findById(productId);
	        if (product.isPresent()) {
	            Product product1 = product.get();
	            List<Review> reviews = reviewRepo.findByProduct(product1);
	            if (!reviews.isEmpty()) {
	                int sumRating = 0;
	                for (Review review : reviews) {
	                    sumRating += review.getRating();
	                }
	                return (double) sumRating / reviews.size();
	            }
	        }
	        return 0.0;
	 }
	
	
}
