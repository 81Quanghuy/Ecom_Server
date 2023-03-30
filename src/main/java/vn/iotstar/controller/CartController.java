package vn.iotstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Cart;
import vn.iotstar.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cart createCart(@RequestBody Cart cart) {
		return cartService.save(cart);
	}

	@GetMapping("list")
	public List<Cart> getCarts() {
		return cartService.findAll();
	}

	@GetMapping("/{id}")
	public Cart getCart(@PathVariable String id) {
		return cartService.findById(id);

	}

	@PutMapping
	public Cart modifyTask(@RequestBody Cart cart) {
		return cartService.updateCart(cart);
	}

	@DeleteMapping("/{id}")
	public String deleteTask(@PathVariable String id) {
		 cartService.deleteById(id);
		 return "Success";
	}
}
