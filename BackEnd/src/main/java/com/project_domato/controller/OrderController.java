package com.project_domato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.OrderDTO;
import com.project_domato.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/place/{user_id}")
	 public ResponseEntity<OrderDTO> placeOrder(@PathVariable Integer user_id) {
        return ResponseEntity.ok(orderService.placeOrder(user_id));
    }
}
