package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Store;

public interface StoreRepository extends MongoRepository<Store, String>{

}
