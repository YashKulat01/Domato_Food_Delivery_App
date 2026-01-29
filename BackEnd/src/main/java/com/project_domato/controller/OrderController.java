package com.project_domato.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.OrderDTO;
import com.project_domato.enums.OrderStatus;
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
	
	
	@GetMapping("/get_orders/{user_id}")
	public ResponseEntity<List<OrderDTO>> getOrderByUser(@PathVariable Integer user_id){
		
		return ResponseEntity.ok(orderService.getOrdersByUser(user_id));
	}
	
	@GetMapping("/get_order/{order_id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer order_id){
		return ResponseEntity.ok(orderService.getOrderById(order_id));
	}
	
	@PutMapping("/{id}/confirm")
	public ResponseEntity<OrderDTO> confirmOrder(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.confirmOrder(id));
	}
	
	@PutMapping("/{id}/prepare")
	public ResponseEntity<OrderDTO> prepareOrder(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.prepareOrder(id));
	}
	
	@PutMapping("/{id}/deliver")
	public ResponseEntity<OrderDTO> deliverOrder(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.deliverOrder(id));
	}
	
	@PutMapping("/{id}/cancel")
	public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Integer id){
		
		return ResponseEntity.ok(orderService.cancelOrder(id));
	}
	
	@GetMapping("/by_status/{status}")
	public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable OrderStatus status){
		
		return ResponseEntity.ok(orderService.getOrdersByStatus(status));
	}
	
	@GetMapping("/get_all")
	public ResponseEntity<List<OrderDTO>> getAllOrders(){
		
		return ResponseEntity.ok(orderService.getAllOrders());
		
	}
	
	@DeleteMapping("/del_order/{id}")
	public ResponseEntity<Map<String, String>> deleteFood(@PathVariable Integer id) {

		orderService.deleteOrder(id);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("Message", "order Deleted!");

		return ResponseEntity.ok(map);
	}
	
}
