package vn.iotstar.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.ImageData;

public interface StorageRepository extends MongoRepository<ImageData, String>{
	
	 Optional<ImageData> findByName(String fileName);
}
