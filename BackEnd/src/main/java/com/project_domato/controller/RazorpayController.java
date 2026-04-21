package com.project_domato.controller;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.Entities.Order;
import com.project_domato.Entities.Payment;
import com.project_domato.enums.OrderStatus;
import com.project_domato.enums.PaymentStatus;
import com.project_domato.repositories.OrderRepository;
import com.project_domato.repositories.PaymentRepository;
import com.project_domato.services.EmailService;
import com.project_domato.services.RazorpayService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/razorpay")
public class RazorpayController {

	@Autowired
	private RazorpayService razorpayService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private EmailService emailService;

	@Value("${Razorpay.key}")
	private String razorpayKey;

	@PostMapping("/create/{orderId}")
	public ResponseEntity<Map<String, Object>> createOrder(@PathVariable Integer orderId) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Order order = orderRepository.findById(orderId).orElseThrow();
			double amount = order.getTotalAmount();
			com.razorpay.Order ro = razorpayService.createOrder(amount);
			// create a payment entry
			Payment payment = new Payment();
			payment.setAmount(amount);
			payment.setMethod("RAZORPAY");
			payment.setPayementStatus(PaymentStatus.PENDING);
			payment.setOrder(order);
			payment.setRazorpayOrderId(ro.get("id"));
			paymentRepository.save(payment);

			resp.put("id", ro.get("id"));
			resp.put("amount", ro.get("amount"));
			resp.put("currency", ro.get("currency"));
			resp.put("key", razorpayKey);
			return ResponseEntity.ok(resp);
		} catch (RazorpayException e) {
			e.printStackTrace();
			resp.put("message", "failed to create order");
			return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/verify")
	public ResponseEntity<Map<String, String>> verifyPayment(@RequestBody Map<String, String> payload) {
		Map<String, String> resp = new HashMap<>();
		try {
			String paymentId = payload.get("paymentId");
			String orderId = payload.get("orderId");
			String signature = payload.get("signature");
			String internalOrderId = payload.get("internalOrderId");

			boolean valid = razorpayService.verifyPayment(paymentId, orderId, signature);
			if (valid) {
				Payment payment = paymentRepository.findByOrderId(Integer.parseInt(internalOrderId)).orElseThrow();
				payment.setPayementStatus(PaymentStatus.SUCCESS);
				payment.setRazorpayPaymentId(paymentId);
				payment.setRazorpayOrderId(orderId);
				payment.setPaymentDate(LocalDateTime.now());
				Payment savedPayment = paymentRepository.save(payment);
				if (savedPayment.getOrder() != null) {
					Order paidOrder = savedPayment.getOrder();
					paidOrder.setOrderStatus(OrderStatus.CONFIRMED);
					orderRepository.save(paidOrder);
					emailService.sendOrderPlacedEmail(savedPayment.getOrder());
				}
				resp.put("message", "Payment verified");
				return ResponseEntity.ok(resp);
			} else {
				if (internalOrderId != null) {
					markPaymentFailedAndCancelOrder(Integer.parseInt(internalOrderId));
				}
				resp.put("message", "Payment verification failed");
				return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("message", "Error verifying payment");
			return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/fail")
	public ResponseEntity<Map<String, String>> markPaymentFailed(@RequestBody Map<String, String> payload) {
		Map<String, String> resp = new HashMap<>();
		try {
			String internalOrderId = payload.get("internalOrderId");
			if (internalOrderId == null) {
				resp.put("message", "internalOrderId is required");
				return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
			}
			markPaymentFailedAndCancelOrder(Integer.parseInt(internalOrderId));
			resp.put("message", "Payment marked failed and order cancelled");
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("message", "Error marking payment failed");
			return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void markPaymentFailedAndCancelOrder(Integer internalOrderId) {
		paymentRepository.findByOrderId(internalOrderId).ifPresent(payment -> {
			payment.setPayementStatus(PaymentStatus.FAILED);
			paymentRepository.save(payment);
		});
		orderRepository.findById(internalOrderId).ifPresent(order -> {
			order.setOrderStatus(OrderStatus.CANCELLED);
			orderRepository.save(order);
		});
	}

}
