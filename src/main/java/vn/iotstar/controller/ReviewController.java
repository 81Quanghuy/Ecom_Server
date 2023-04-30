package vn.iotstar.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Product;
import vn.iotstar.entity.Review;
import vn.iotstar.entity.User;
import vn.iotstar.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	ReviewService review;
	
	@GetMapping("list")
	public List<Review> getReviewAll() {
		return review.findAll();
	}

	@PostMapping("/add")
	public Review addOrderItem(@RequestBody Review reiReview) {
		Review entity = reiReview;
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return review.save(reiReview);
	}
	
	@PostMapping("/product")
	public List<Review> getByProduct(@RequestBody Product product) {
		return review.findByProduct(product);
	}
	
	@PostMapping("/users")
	public List<Review> getByUser(@RequestBody User user) {
		return review.findByUser(user);
	}
	
}