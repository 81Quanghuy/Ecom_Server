package vn.iotstar.controller;


import java.util.ArrayList;
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

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.ERole;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Wishlist;
import vn.iotstar.model.WhislistModel;
import vn.iotstar.service.CartService;
import vn.iotstar.service.UserService;
import vn.iotstar.service.WishListService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	WishListService wishListService;
	
	@Autowired
	CartService cartService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password ) {
		List<User> list = userService.findAll();
		for (User user : list) {
			if(user.getUsername().toString().equals(username)) {
				return null;
			}
//			if(user.getPhone().toString().equals(entity.getPhone())) {
//				return null;
//			}
//			if(user.getEmail().toString().equals(entity.getEmail())) {
//				return null;
//			}
		} 
		return userService.createUser(username,password);
	}

	@PostMapping("/get")
	public User getUserByUsername(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password ) {
		return userService.getUserByUsername(username, password);
	}
	@PostMapping("/getRole")
	public User getManagerByUsername(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password ) {
		User entity = userService.getUserByUsername(username, password);
		if(entity.getRole().equals(ERole.ROLE_MANAGER.toString())|| entity.getRole().equals(ERole.ROLE_ADMIN.toString())) {
			return entity;
		}
		return null;
	}
	@GetMapping("list")
	public List<User> getUsers() {
		return userService.findAll();
	}

	@PostMapping("/getUserById")
	public User getUser(@RequestParam(name = "id", required = false) String id) {
		return userService.findById(id);

	}

	@PostMapping("/updateUser")
	public User modifyUser(@RequestBody User user) {
		//Khai báo User,Danh sách User
		User entity = user;

		List<User> list = userService.findAll();
		List<User> listExpUser = new ArrayList<User>();
		
		// Tìm các User còn lại
		for(User us :list) {
			if(!us.getId().toString().equals(user.getId())) {
				listExpUser.add(us);
			}
		}
		// Hàm điều kiện 
		for (User user1 : listExpUser) {
			if(user1.getUsername().toString().equals(entity.getUsername())) {
				return null;
			}
			if(user1.getPhone().toString().equals(entity.getPhone())) {
				return null;
			}
			if(user1.getEmail().toString().equals(entity.getEmail())) {
				return null;
			}
		} 
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
			user.setIsActive(false);	
			userService.updateUser(user);
			return "Success";
		}
		catch (Exception e) {
			return e.getMessage();
		}
		
	}
	@PostMapping("active-run")
	public User Active(@RequestParam(name = "id", required = false) String id){
		try {
			User user = userService.findById(id);
			if(user.getIsActive()) {
				user.setIsActive(false);	
			}
			else {
				user.setIsActive(true);	
			}
				
			return userService.updateUser(user);
		}
		catch (Exception e) {
			return null;
		}
		
	}
	@PostMapping("add")
	public User AddUser(@RequestBody User user) {
		User entity = user;
		if(user.getAvatar()=="") {
			user.setAvatar("https://ecomserver.up.railway.app/images/IT.jpg");
		}

		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		entity.setIsActive(true);
		entity.setRole(ERole.ROLE_USER.toString());
		entity.setResetpasswordtoken(user.getPassword());

		List<User> list = userService.findAll();
		for (User user1 : list) {
			if(user1.getUsername().toString().equals(entity.getUsername())) {
				return null;
			}
			if(user1.getPhone().toString().equals(entity.getPhone())) {
				return null;
			}
			if(user1.getEmail().toString().equals(entity.getEmail())) {
				return null;
			}
		}
		
		Cart cart = new Cart();
		List<CartItem> cartItem = new ArrayList<>();
		cart.setUser(user);
		cart.setCartItems(cartItem);
		cart.setId(UUID.randomUUID().toString().split("-")[0]);
		cartService.save(cart);
		
		return userService.save(entity);
	}
	@PostMapping("active")
	public List<User>getUsersActive(@RequestParam(name = "isActive", required = false) Boolean isActive){
		return userService.findByIsActive(isActive);	
	}

	
	@PostMapping("addWishlist")
	public Wishlist add(@RequestBody Wishlist wishlist) {
		Wishlist entity = wishlist;
		List<Wishlist> oldwishlist = wishListService.findByUser(entity.getUser());
		if(oldwishlist != null) {
			List<Product> products = new ArrayList<>();
			products.addAll(oldwishlist.get(0).getProducts());
			products.addAll(entity.getProducts());
			oldwishlist.get(0).setProducts(products);
			return wishListService.save(oldwishlist.get(0));
		}
		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		return wishListService.save(entity);
	}
	
	  @PostMapping("userwishlist")
	  public WhislistModel getWishlistByUser(@RequestBody User user) {
	        String message = "User not found";
	        if(user != null) {
		        List<Wishlist> wisthlist = wishListService.findByUser(user);
		        message = "success";
		        return new WhislistModel(message, wisthlist.get(0).getProducts());
	        }
			return new WhislistModel(message, null);
	   }
		
	
}
