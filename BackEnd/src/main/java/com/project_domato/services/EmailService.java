package com.project_domato.services;

import com.project_domato.Entities.Order;

public interface EmailService {
	void sendOrderPlacedEmail(Order order);

	void sendOrderStatusEmail(Order order);
}
