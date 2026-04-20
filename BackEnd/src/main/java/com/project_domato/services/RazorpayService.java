package com.project_domato.services;

import com.razorpay.Order;
import com.razorpay.RazorpayException;

public interface RazorpayService {
	Order createOrder(double amount) throws RazorpayException;
	boolean verifyPayment(String paymentId, String orderId, String signature) throws RazorpayException;
}
