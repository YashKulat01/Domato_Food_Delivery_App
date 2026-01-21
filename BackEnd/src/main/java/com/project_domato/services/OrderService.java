package com.project_domato.services;

import java.util.List;

import com.project_domato.dtos.OrderDTO;
import com.project_domato.enums.OrderStatus;

public interface OrderService {

	OrderDTO placeOrder(Integer userId);
	
	List<OrderDTO> getOrdersByUser(Integer userId);
	
	OrderDTO getOrderById(Integer orderId);
	
	OrderDTO confirmOrder(Integer orderId);
	
	OrderDTO prepareOrder(Integer orderId);
	
	OrderDTO deliverOrder(Integer orderId);
	
	OrderDTO cancelOrder(Integer orderId);
	
	List<OrderDTO> getOrdersByStatus(OrderStatus orderStatus);
	
	List<OrderDTO> getAllOrders();
	
}
