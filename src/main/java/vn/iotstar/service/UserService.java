package vn.iotstar.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public <S extends User> S save(S entity) {
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return userRepo.save(entity);
	}

	public <S extends User> S insert(S entity) {
		return userRepo.insert(entity);
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findById(String id) {
		return userRepo.findById(id).get();
	}

	public long count() {
		return userRepo.count();
	}

	public void deleteById(String id) {
		userRepo.deleteById(id);
	}

	public void delete(User entity) {
		userRepo.delete(entity);
	}

	public void deleteAll() {
		userRepo.deleteAll();
	}

	public User updateUser(User userRequest) {
		// TODO Auto-generated method stub
		// get the existing document from DB
		// populate new value from request to existing object/entity/document
		User existingUser = userRepo.findById(userRequest.getId()).get();
		existingUser.setAddress(userRequest.getAddress());
		existingUser.setAvatar(userRequest.getAvatar());
		existingUser.setCreateat(userRequest.getCreateat());
		existingUser.setEmail(userRequest.getEmail());
		existingUser.setFirstName(userRequest.getFirstName());
		existingUser.setIsActive(userRequest.getIsActive());
		existingUser.setLastName(userRequest.getLastName());
		existingUser.setPassword(userRequest.getPassword());
		existingUser.setPhone(userRequest.getPhone());
		existingUser.setStores(userRequest.getStores());
		existingUser.setUsername(userRequest.getUsername());
		existingUser.setUpdateat(userRequest.getUpdateat());

		return userRepo.save(existingUser);
	}
	public User getUserByUsername(String username, String password) {
		return userRepo.getUserByUsernameAndPassword(username, password);
	}

	public User createUser(String username, String password) {
		User entity = new User();
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		entity.setUsername(username);
		entity.setPassword(password);
		return userRepo.save(entity);
	}
}
