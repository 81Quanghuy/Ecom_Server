package vn.iotstar.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.ERole;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password ) {
		List<User> list = userService.findAll();
		for (User user : list) {
			if(user.getUsername().toString().equals(username)) {
				return null;
			}
		} 
		return userService.createUser(username,password);
	}

	@PostMapping("/get")
	public User getUserByUsername(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password ) {
		return userService.getUserByUsername(username, password);
	}
	@GetMapping("list")
	public List<User> getUsers() {
		return userService.findAll();
	}

	@PostMapping("/getUserById")
	public User getUser(@RequestParam(name = "id", required = false) String id) {
		return userService.findById(id);

	}

	@PostMapping("updateUser")
	public User modifyUser(@RequestBody User user) {
		User entity = user;
		Date currentDate = new Date();
		entity.setUpdateat(currentDate);
		return userService.save(entity);
	}

	@PostMapping("/delete")
	public String deleteTask(@RequestParam(name  = "id", required = false) String id) {
		userService.deleteById(id);
		 return "Success";
	}
	@PostMapping("remove")
	public String UnActive(@RequestParam(name = "id", required = false) String id){
		try {
			User user = userService.findById(id);
			Date currentDate = new Date();
			user.setIsActive(false);	
			user.setUpdateat(currentDate);
			userService.updateUser(user);
			return "Success";
		}
		catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
	}
	@PostMapping("active-run")
	public String Active(@RequestParam(name = "id", required = false) String id){
		try {
			User user = userService.findById(id);
			Date currentDate = new Date();
			if(user.getIsActive()) {
				user.setIsActive(false);	
			}
			else {
				user.setIsActive(true);	
			}
			
			user.setUpdateat(currentDate);
			userService.updateUser(user);
			return "Success";
		}
		catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
	}
	@PostMapping("add")
	public User AddUser(@RequestBody User user) {
		User entity = user;
		if(user.getAvatar() ==null) {
			user.setAvatar("https://ecomserver.up.railway.app/images/IT.jpg");
		}
		Date currentDate = new Date();
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		entity.setIsActive(true);
		entity.setRole(ERole.ROLE_USER.toString());
		entity.setResetpasswordtoken(user.getPassword());
		entity.setCreateat(currentDate);
		entity.setUpdateat(currentDate);
		return userService.save(entity);
	}
	@PostMapping("active")
	public List<User>getUsersActive(@RequestParam(name = "isActive", required = false) Boolean isActive){
		return userService.findByIsActive(isActive);	
	}
}
