package vn.iotstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Review;
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
		return review.save(reiReview);
	}
	
}
