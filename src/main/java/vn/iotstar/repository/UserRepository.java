package vn.iotstar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import vn.iotstar.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

    @Query("{ username: ?0, password: ?1 }")
    User getUserByUsernameAndPassword(String username, String password);
}
