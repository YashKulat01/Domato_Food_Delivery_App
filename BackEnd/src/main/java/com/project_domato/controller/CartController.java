package com.project_domato.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.CartDTO;
import com.project_domato.services.CartService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/add_cart")
	public ResponseEntity<CartDTO> addToCart(@RequestParam Integer userId, @RequestParam Integer foodId,
			@RequestParam Integer quantity) {
		// TODO: process POST request

		CartDTO newCart = cartService.addToCart(userId, foodId, quantity);
		return new ResponseEntity<CartDTO>(newCart, HttpStatus.CREATED);
	}

	@GetMapping("/get_cart/{user_id}")
	public ResponseEntity<CartDTO> getCart(@PathVariable Integer user_id) {

		return ResponseEntity.ok(cartService.getCartByUserId(user_id));
	}

	@GetMapping("/all_carts")
	public ResponseEntity<List<CartDTO>> allCarts() {

		return ResponseEntity.ok(cartService.getAllCarts());
	}

	@DeleteMapping("/remove_item")
	public ResponseEntity<CartDTO> removeFromCart(@RequestParam Integer userId, @RequestParam Integer foodId) {

		return ResponseEntity.ok(cartService.removeFromCart(userId, foodId));
	}

	@DeleteMapping("/clr_cart/{id}")
	public ResponseEntity<Map<String, String>> clearCart(@PathVariable Integer id) {

		cartService.clearCart(id);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("Message", "Cart Cleared !");

		return ResponseEntity.ok(map);
	}
	
	@DeleteMapping("/del_cart/{id}")
	public ResponseEntity<Map<String, String>> deleteCart(@PathVariable Integer id) {

		cartService.deleteCart(id);
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("Message", "Cart Deleted !");

		return ResponseEntity.ok(map);
	}
	
}
