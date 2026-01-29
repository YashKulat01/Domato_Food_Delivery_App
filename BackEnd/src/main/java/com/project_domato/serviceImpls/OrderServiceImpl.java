package com.project_domato.serviceImpls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_domato.exception.NotFoundException;
import com.project_domato.Entities.Cart;
import com.project_domato.Entities.CartItems;
import com.project_domato.Entities.Order;
import com.project_domato.Entities.OrderItems;
import com.project_domato.Entities.User;
import com.project_domato.dtos.OrderDTO;
import com.project_domato.enums.OrderStatus;
import com.project_domato.repositories.CartRepository;
import com.project_domato.repositories.OrderRepository;
import com.project_domato.repositories.UserRepository;
import com.project_domato.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OrderDTO placeOrder(Integer userId) {
		// TODO Auto-generated method stub

		// CHECK IF THE USER IS PRESENT OR NOT;
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found !"));

		Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("Cart Not Found !"));

		Order order = new Order();
		order.setUser(user);

		order.setOrderDate(LocalDateTime.now());

		order.setOrderStatus(OrderStatus.PLACED);

		List<OrderItems> orderItems = new ArrayList<>();

		double totalAmt = 0.0;

		for (CartItems cartItem : cart.getItems()) {

			OrderItems items = new OrderItems();

			items.setFood(cartItem.getFood());

			items.setQuantity(cartItem.getQuantity());
			items.setPrice(cartItem.getFood().getFoodPrice());
			items.setOrder(order);

			totalAmt += cartItem.getQuantity() * cartItem.getFood().getFoodPrice();
			orderItems.add(items);
		}

		double deliveryFee = 2.0;

		order.setTotalAmount(totalAmt + deliveryFee);

		order.setOrderItems(orderItems);
		
		// EMPTY CART AFTER AN ORDER
		cart.getItems().clear();

		Order savedOrder = orderRepository.save(order);

		return toOrderDTO(savedOrder);
	}

	private OrderDTO toOrderDTO(Order order) {
		OrderDTO dto = modelMapper.map(order, OrderDTO.class);
		if (dto.getOrderItems() != null && order.getOrderItems() != null) {
			for (int i = 0; i < order.getOrderItems().size() && i < dto.getOrderItems().size(); i++) {
				dto.getOrderItems().get(i).setFoodName(order.getOrderItems().get(i).getFood().getFoodName());
			}
		}
		return dto;
	}

	@Override
	public List<OrderDTO> getOrdersByUser(Integer userId) {
		return orderRepository.findByUserId(userId).stream().map(this::toOrderDTO).toList();
	}

	@Override
	public OrderDTO getOrderById(Integer orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not Found !"));
		return toOrderDTO(order);
	}

	@Override
	public OrderDTO confirmOrder(Integer orderId) {
		// TODO Auto-generated method stub

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order Not Found !"));

		order.setOrderStatus(OrderStatus.CONFIRMED);

		Order savedOrder = orderRepository.save(order);

		return toOrderDTO(savedOrder);
	}

	@Override
	public OrderDTO prepareOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order Not Found !"));

		order.setOrderStatus(OrderStatus.PREPARING);

		Order savedOrder = orderRepository.save(order);

		return toOrderDTO(savedOrder);
	}

	@Override
	public OrderDTO deliverOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order Not Found !"));

		order.setOrderStatus(OrderStatus.DELIVERED);

		Order savedOrder = orderRepository.save(order);

		return modelMapper.map(savedOrder, OrderDTO.class);
	}

	@Override
	public OrderDTO cancelOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order Not Found !"));

		order.setOrderStatus(OrderStatus.CANCELLED);

		Order savedOrder = orderRepository.save(order);

		return toOrderDTO(savedOrder);
	}

	@Override
	public List<OrderDTO> getOrdersByStatus(OrderStatus orderStatus) {
		// TODO Auto-generated method stub

		return orderRepository.findByOrderStatus(orderStatus).stream().map(this::toOrderDTO).toList();
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		return orderRepository.findAll().stream().map(this::toOrderDTO).toList();
	}

	@Override
	public void deleteOrder(Integer orderId) {
		// TODO Auto-generated method stub

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not Found !"));

		orderRepository.delete(order);
	}

}
