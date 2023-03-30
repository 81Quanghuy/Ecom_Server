package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Category;

public interface CategoryRepository extends MongoRepository<Category, String>{

}
