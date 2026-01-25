package com.project_domato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.PaymentDTO;
import com.project_domato.enums.PaymentStatus;
import com.project_domato.services.PaymentService;

@RestController
@RequestMapping("/payments")

public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/add/{order_id}")
	public ResponseEntity<PaymentDTO> addPayment(@RequestBody PaymentDTO paymentDTO, @PathVariable Integer order_id) {

		return ResponseEntity.ok(paymentService.createPayment(paymentDTO, order_id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentDTO> getPaymentByOrderId(@PathVariable Integer id) {

		return ResponseEntity.ok(paymentService.getPaymentByOrderId(id));
	}

//	@PutMapping("/{paymentId}/{status}")
//	public ResponseEntity<PaymentDTO> updatePaymentStatus(@PathVariable Integer paymentId,
//			@RequestParam String status) {
//
//		return ResponseEntity.ok(paymentService.updatePaymentStatus(paymentId, status));
//	}

	@GetMapping("/by_status/{status}")
	public ResponseEntity<List<PaymentDTO>> getPaymentByStatus(@PathVariable PaymentStatus status) {

		return ResponseEntity.ok(paymentService.getPayementsByStatus(status));
	}

}
