package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Product;

public interface ProductRepository extends MongoRepository<Product,String>{

}
