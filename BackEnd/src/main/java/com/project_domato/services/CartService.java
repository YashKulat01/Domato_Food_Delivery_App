package com.project_domato.services;

import java.util.List;

import com.project_domato.dtos.CartDTO;

public interface CartService {

	CartDTO addToCart(Integer userId,Integer foodId,Integer quantity);
	
	CartDTO getCartByUserId(Integer userId);
	
	CartDTO removeFromCart(Integer userId, Integer foodId);
	
	void deleteCart(Integer userId);
	
	List<CartDTO> getAllCarts();
	
}
