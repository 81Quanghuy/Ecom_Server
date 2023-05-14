package vn.iotstar.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Product;
import vn.iotstar.entity.Review;
import vn.iotstar.entity.User;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	ReviewService review;

	@Autowired
	ProductService productService;

	@GetMapping("list")
	public List<Review> getReviewAll() {
		return review.findAll();
	}

	@PostMapping("/add")
	public Review addOrderItem(@RequestBody Review reiReview) {
		Review entity = reiReview; // chỉnh ngày tháng năm thành dạng dd/mm/yyyy
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(date);
		entity.setCreateat(strDate);
		entity.setUpdateat(strDate);
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		Double rating = review.calculateAverageRatingByProduct(entity.getProduct().getId());
		Optional<Product> pro = productService.findById(entity.getProduct().getId());
		if(pro.isPresent()) {
				pro.get().setRating(rating);
				productService.save(pro.get());
		}
		//entity.setProduct(p);
		return review.save(entity);

	}

	@PostMapping("/product")
	public List<Review> getByProduct(@RequestBody Product product) {
		return review.findByProduct(product);
	}

	@PostMapping("/rating")
	public Double getRatingProduct(@RequestParam(name = "id", required = false) String id) {
		return review.calculateAverageRatingByProduct(id);
	}

	@PostMapping("/users")
	public List<Review> getByUser(@RequestBody User user) {
		return review.findByUser(user);
	}

}