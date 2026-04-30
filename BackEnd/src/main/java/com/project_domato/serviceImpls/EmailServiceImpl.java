package com.project_domato.serviceImpls;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project_domato.Entities.Order;
import com.project_domato.Entities.OrderItems;
import com.project_domato.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;

	@Value("${spring.mail.username:}")
	private String fromEmail;

	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendOrderPlacedEmail(Order order) {
		String subject = "Domato: Order #" + order.getId() + " placed successfully";
		String body = buildOrderDetails(order, "Your order has been placed after successful payment.");
		send(order, subject, body);
	}

	@Override
	public void sendOrderStatusEmail(Order order) {
		String readableStatus = order.getOrderStatus() != null ? order.getOrderStatus().name() : "UPDATED";
		String subject = "Domato: Order #" + order.getId() + " is " + readableStatus;
		String body = buildOrderDetails(order, "Your order status is now: " + readableStatus + ".");
		send(order, subject, body);
	}

	private String buildOrderDetails(Order order, String intro) {
		String items = order.getOrderItems() == null ? "No items"
				: order.getOrderItems().stream().map(this::formatItem).collect(Collectors.joining("\n"));
		return intro + "\n\n" + "Order ID: " + order.getId() + "\n" + "Status: " + order.getOrderStatus() + "\n"
				+ "Total Amount: Rs " + order.getTotalAmount() + "\n" + "Order Date: " + order.getOrderDate() + "\n\n"
				+ "Items:\n" + items + "\n\n" + "Thank you for ordering with Domato.";
	}

	private String formatItem(OrderItems item) {
		String name = item.getFood() != null ? item.getFood().getFoodName()
				: (item.getFoodName() != null ? item.getFoodName() : "Removed item");
		Double price = item.getFood() != null ? item.getFood().getFoodPrice() : item.getPrice();
		return "- " + name + " x " + item.getQuantity() + " = " + (price * item.getQuantity());
	}

	private void send(Order order, String subject, String body) {
		if (order == null || order.getUser() == null || order.getUser().getEmail() == null
				|| order.getUser().getEmail().isBlank()) {
			return;
		}
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			if (fromEmail != null && !fromEmail.isBlank()) {
				message.setFrom(fromEmail);
			}
			message.setTo(order.getUser().getEmail());
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
		} catch (Exception ex) {
			// Ignore mail failures to avoid blocking order flow.
		}
	}
}
