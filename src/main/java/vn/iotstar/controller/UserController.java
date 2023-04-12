package vn.iotstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public User modifyUser(@RequestBody User User) {
		return userService.save(User);
	}

	@DeleteMapping("/{id}")
	public String deleteTask(@PathVariable String id) {
		userService.deleteById(id);
		 return "Success";
	}
	@PostMapping("remove")
	public String UnActive(@RequestParam(name = "id", required = false) String id){
		try {
			User user = userService.findById(id);
			user.setIsActive(false);	
			userService.updateUser(user);
			return "Success";
		}
		catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
	}
}
