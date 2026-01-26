package com.project_domato.services;

import java.util.List;

import com.project_domato.dtos.PaymentDTO;
import com.project_domato.enums.PaymentStatus;

public interface PaymentService {

	PaymentDTO createPayment(PaymentDTO paymentDTO,Integer orderId);
	
	PaymentDTO getPaymentByOrderId(Integer orderId);
	
//	PaymentDTO updatePaymentStatus(Integer paymentId,String paymentStatus);
	
	List<PaymentDTO> getPayementsByStatus(PaymentStatus paymentStatus);
}
