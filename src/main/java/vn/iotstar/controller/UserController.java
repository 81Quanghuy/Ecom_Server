package vn.iotstar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import vn.iotstar.entity.WishlistRequest;
import vn.iotstar.model.ChangePasswordRequest;
import vn.iotstar.model.ForgotPasswordRequest;
import vn.iotstar.model.ForgotPasswordRes;
import vn.iotstar.model.ResetPasswordRequest;
import vn.iotstar.model.WhislistModel;
import vn.iotstar.model.WishListRequest;
import vn.iotstar.model.WishlistResponse;
import vn.iotstar.service.CartService;
import vn.iotstar.service.EmailService;
import vn.iotstar.service.UserService;
import vn.iotstar.service.WishListService;
import java.security.SecureRandom;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final String OTP_CHARACTERS = "0123456789";
    private static final int OTP_LENGTH = 6;

	@Autowired
	private UserService userService;
	@Autowired
	WishListService wishListService;

	@Autowired
	CartService cartService;

	@Autowired
	EmailService emailService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) {
		List<User> list = userService.findAll();
		for (User user : list) {
			if (user.getUsername().toString().equals(username)) {
				return null;
			}
//			if(user.getPhone().toString().equals(entity.getPhone())) {
//				return null;
//			}
//			if(user.getEmail().toString().equals(entity.getEmail())) {
//				return null;
//			}
		}
		return userService.createUser(username, password);
	}

	@PostMapping("/get")
	public User getUserByUsername(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) {
		User user = userService.getUserByUsername(username, password);
		if(user.getIsActive()) {
			return user;
		}
		return null;
	}

	@PostMapping("/getRole")
	public User getManagerByUsername(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) {
		User entity = userService.getUserByUsername(username, password);
		if (entity.getRole().equals(ERole.ROLE_MANAGER.toString())
				|| entity.getRole().equals(ERole.ROLE_ADMIN.toString())) {
			if(entity.getIsActive()) {
				return entity;
			}
			
		}
		return null;
	}

	@GetMapping("list")
	public List<User> getUsers() {
		List<User> users = userService.findAll();
		
		List<User> userList = new ArrayList<>();
		for (User user2 : users) {
			if(user2.getRole().equals("ROLE_USER")) {
				userList.add(user2);
			}
		}
		return userList;
	}

	@PostMapping("/getUserById")
	public User getUser(@RequestParam(name = "id", required = false) String id) {
		return userService.findById(id);

	}

	@PostMapping("/updateUser")
	public User modifyUser(@RequestBody User user) {
		// Khai báo User,Danh sách User
		User entity = user;

		List<User> list = userService.findAll();
		List<User> listExpUser = new ArrayList<User>();

		// Tìm các User còn lại
		for (User us : list) {
			if (!us.getId().toString().equals(user.getId())) {
				listExpUser.add(us);
			}
		}
		// Hàm điều kiện
		for (User user1 : listExpUser) {
			if (user1.getUsername().toString().equals(entity.getUsername())) {
				return null;
			}
			if (user1.getPhone().toString().equals(entity.getPhone())) {
				return null;
			}
			if (user1.getEmail().toString().equals(entity.getEmail())) {
				return null;
			}
		}
		return userService.save(entity);
	}

	@PostMapping("/delete")
	public String deleteTask(@RequestParam(name = "id", required = false) String id) {
		userService.deleteById(id);
		return "Success";
	}

	@PostMapping("remove")
	public String UnActive(@RequestParam(name = "id", required = false) String id) {
		try {
			User user = userService.findById(id);
			user.setIsActive(false);
			userService.updateUser(user);
			return "Success";
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@PostMapping("active-run")
	public User Active(@RequestParam(name = "id", required = false) String id){
			User user = userService.findById(id);
			if(user != null) {
				if(user.getIsActive()) {
					user.setIsActive(false);	
				}
				else {
					user.setIsActive(true);	
				}
					
				return userService.updateUser(user);
			}
			return null;
	}

	@PostMapping("add")
	public User AddUser(@RequestBody User user) {
		User entity = user;
		if (user.getAvatar() == "") {
			user.setAvatar("https://ecomserver.up.railway.app/images/IT.jpg");
		}

		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		entity.setIsActive(true);
		entity.setRole(ERole.ROLE_USER.toString());
		entity.setResetpasswordtoken(user.getPassword());

		List<User> list = userService.findAll();
		for (User user1 : list) {
			if (user1.getUsername().toString().equals(entity.getUsername())) {
				return null;
			}
			if (user1.getPhone().toString().equals(entity.getPhone())) {
				return null;
			}
			if (user1.getEmail().toString().equals(entity.getEmail())) {
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
	public List<User> getUsersActive(@RequestParam(name = "isActive", required = false) Boolean isActive) {
		return userService.findByIsActive(isActive);
	}

	@PostMapping("/addWishlist")
	public WishlistResponse addWishlist(@RequestBody Wishlist wishlist) {
		String message;
		List<Wishlist> list = wishListService.findByUser(wishlist.getUser());

		if (list.isEmpty()) {
			Wishlist newWishlist = new Wishlist();
			newWishlist.setUser(wishlist.getUser());
			newWishlist.setProducts(wishlist.getProducts());
			newWishlist.setId(UUID.randomUUID().toString().split("-")[0]);
			Wishlist savedWishlist = wishListService.save(newWishlist);
			message = "success";

			return new WishlistResponse(message, savedWishlist.getUser(), savedWishlist.getProducts().get(0));
		}

		Wishlist existingWishlist = list.get(0);
		List<Product> existingProducts = existingWishlist.getProducts();
		existingProducts.addAll(wishlist.getProducts());
		existingWishlist.setProducts(existingProducts);

		Wishlist updatedWishlist = wishListService.save(existingWishlist);
		message = "Wishlist update complete";

		return new WishlistResponse(message, updatedWishlist.getUser(), updatedWishlist.getProducts().get(0));
	}

	@PostMapping("userwishlist")
	public WishListRequest getWishlistByUser(@RequestBody User user) {
		String message = "User not found";
		if (user != null) {
			List<Wishlist> wisthlist = wishListService.findByUser(user);
			message = "success";
			return new WishListRequest(message, wisthlist.get(0).getProducts());
		}
		return new WishListRequest(message, null);
	}

	@GetMapping("wishlist")
	public List<Wishlist> getWishlists(@RequestBody User user) {
		return wishListService.findByUser(user);
	}

	@PostMapping("/deleteWishlist")
	public String removeProductFromWishlist(@RequestBody WishlistRequest request) {
		List<Wishlist> wishlist = wishListService.findByUser(request.getUser());

		if (wishlist.isEmpty()) {
			return "Không tìm thấy wishlist cho người dùng: " + request.getUser().getId();
		}

		Wishlist userWishlist = wishlist.get(0);
		List<Product> products = userWishlist.getProducts();

		boolean productFound = false;

		for (Product p : products) {
			if (p.getId().equals(request.getProduct().getId())) {
				products.remove(p);
				productFound = true;
				break;
			}
		}

		if (!productFound) {
			return "Không tìm thấy sản phẩm trong wishlist của người dùng: " + request.getProduct().getId();
		}

		userWishlist.setProducts(products);
		wishListService.save(userWishlist);
		return "Sản phẩm " + request.getProduct().getId() + " đã được xoá khỏi wishlist của người dùng: "
				+ request.getUser().getId();
	}
	
	@PostMapping("/forgotPassword")
	public ForgotPasswordRes forgotPassword(@RequestParam(name = "email", required = false) String email) {
		
		// Kiểm tra email có tồn tại trong hệ thống
		if (!userService.isEmailExists(email)) {
			return new ForgotPasswordRes(0,"Email không tồn tại");
		}

		// Sinh OTP
		String otp = generateOTP();

		// Lưu OTP và gửi email
		userService.saveOTP(email, otp);
		sendEmail(email, otp);
		return new ForgotPasswordRes(1,"OTP đã được gửi đến email");
	}
//
//	@PostMapping("/forgotPassword")
//	public ResponseEntity<String> forgotPassword(@RequestParam(name = "email", required = false) String email) {
//		
//		// Kiểm tra email có tồn tại trong hệ thống
//		if (!userService.isEmailExists(email)) {
//			return ResponseEntity.badRequest().body("Email không tồn tại");
//		}
//
//		// Sinh OTP
//		String otp = generateOTP();
//
//		// Lưu OTP và gửi email
//		userService.saveOTP(email, otp);
//		sendEmail(email, otp);
//		return ResponseEntity.ok("OTP đã được gửi đến email");
//	}

	private void sendEmail(String toEmail, String otp) {
		String subject = "Reset Password OTP";
		String body = "<html><body>"
                + "<h1>OTP Confirmation</h1>"
                + "<p>Your OTP: " + otp + "</p>"
                + "<p>Please enter this OTP to confirm your action.</p>"
                + "</body></html>";
		emailService.sendEmail(toEmail, subject, body);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
		String username = request.getUsername();
		String currentPassword = request.getCurrentPassword();
		String newPassword = request.getNewPassword();

		// Kiểm tra xác thực người dùng và mật khẩu hiện tại
		if (!userService.authenticate(username, currentPassword)) {
			return ResponseEntity.badRequest().body("Sai tên đăng nhập hoặc mật khẩu hiện tại");
		}

		// Thay đổi mật khẩu mới
		if (!userService.changePassword(username, newPassword)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thay đổi mật khẩu");
		}

		return ResponseEntity.ok("Thay đổi mật khẩu thành công");
	}

//	@PostMapping("/resetPassword")
//	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
//		String email = request.getEmail();
//		String otp = request.getOtp();
//		String newPassword = request.getNewPassword();
//
//		// Kiểm tra OTP xác thực
//		if (!userService.isValidOTP(email, otp)) {
//			return ResponseEntity.badRequest().body("OTP không hợp lệ");
//		}
//
//		// Thay đổi mật khẩu mới
//		if (!userService.changePasswordByEmail(email, newPassword)) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thay đổi mật khẩu");
//		}
//
//		return ResponseEntity.ok("Thay đổi mật khẩu thành công");
//	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
		String email = request.getEmail();
		String otp = request.getOtp();
		String newPassword = request.getNewPassword();

		// Kiểm tra OTP xác thực
		if (!userService.isValidOTP(email, otp)) {
			return ResponseEntity.badRequest().body("OTP không hợp lệ");
		}

		// Thay đổi mật khẩu mới
		if (!userService.changePasswordByEmail(email, newPassword)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thay đổi mật khẩu");
		}

		return ResponseEntity.ok("Thay đổi mật khẩu thành công");
	}
	

	private String generateOTP() {

		  StringBuilder otp = new StringBuilder();
	        SecureRandom random = new SecureRandom();

	        for (int i = 0; i < OTP_LENGTH; i++) {
	            int index = random.nextInt(OTP_CHARACTERS.length());
	            char character = OTP_CHARACTERS.charAt(index);
	            otp.append(character);
	        }

	        return otp.toString();
	}
	
	@PostMapping("/checkOTP")
	public String checkMail(@RequestParam(name = "mail", required = false) String mail,@RequestParam(name = "otp", required = false) String otp ) {
		return userService.isValidOTP(mail, otp)+"";
	}
}
