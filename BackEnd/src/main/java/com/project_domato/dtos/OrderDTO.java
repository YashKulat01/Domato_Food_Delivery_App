package com.project_domato.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.project_domato.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTO {
	
	private Integer id;
	private Integer userId;
	
	private Double totalAmount;
	
	private OrderStatus orderStatus;
	
	private LocalDateTime orderDate;
	
	private List<OrderItemsDTO> orderItems;
}
