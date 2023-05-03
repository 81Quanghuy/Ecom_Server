package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Category;

public interface CategoryRepository extends MongoRepository<Category, String>{

	List<Category> findByName(String name);
}
