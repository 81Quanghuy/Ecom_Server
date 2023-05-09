package vn.iotstar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.User;
import vn.iotstar.service.CartItemService;
import vn.iotstar.service.CartService;

@RestController
@RequestMapping("/cart")
public class WishListController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartItemService cartItemService;
	
	@PostMapping("add")
	public Cart insertCart(@RequestBody User user ) {

		Cart cart = new Cart();
		List<CartItem> cartItem = new ArrayList<>();
		cart.setUser(user);
		cart.setCartItems(cartItem);
		cart.setId(UUID.randomUUID().toString().split("-")[0]);
		cartService.save(cart);
		Cart cart1 = cartService.save(cart);
		return cartService.save(cart);	
	}
	
	@PostMapping("addCartItem")
	public CartItem insertCartItem(@RequestBody CartItem cartItem) {
		
		CartItem entity = new CartItem();
		
		return cartItemService.save(cartItem);
	}
	

}
