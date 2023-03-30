package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Review;

public interface ReviewRepository extends MongoRepository<Review, String>{

}
