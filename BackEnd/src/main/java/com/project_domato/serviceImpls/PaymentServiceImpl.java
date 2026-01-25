package com.project_domato.serviceImpls;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_domato.Entities.Order;
import com.project_domato.Entities.Payment;
import com.project_domato.dtos.PaymentDTO;
import com.project_domato.enums.PaymentStatus;
import com.project_domato.exception.NotFoundException;
import com.project_domato.repositories.OrderRepository;
import com.project_domato.repositories.PaymentRepository;
import com.project_domato.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PaymentDTO createPayment(PaymentDTO paymentDTO,Integer orderId) {
		// TODO Auto-generated method stub

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new NotFoundException("Order not Found !"));

		Payment payment = modelMapper.map(paymentDTO, Payment.class);

		payment.setAmount(order.getTotalAmount());
		payment.setMethod(paymentDTO.getMethod());
		payment.setPayementStatus(PaymentStatus.PENDING);
		payment.setOrder(order);
		payment.setPaymentDate(LocalDateTime.now());
		Payment savedPayment = paymentRepository.save(payment);

		return modelMapper.map(savedPayment, PaymentDTO.class);
	}

	@Override
	public PaymentDTO getPaymentByOrderId(Integer orderId) {
		// TODO Auto-generated method stub

		Payment payment = paymentRepository.findById(orderId)
				.orElseThrow(() -> new NotFoundException("Payment Not Found !"));

		return modelMapper.map(payment, PaymentDTO.class);
	}

	@Override
	public PaymentDTO updatePaymentStatus(Integer paymentId, PaymentStatus paymentStatus) {
		// TODO Auto-generated method stub

		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new NotFoundException("Payment Not Found !"));

		payment.setPayementStatus(paymentStatus);

		Payment updatedPayment = paymentRepository.save(payment);

		return modelMapper.map(updatedPayment, PaymentDTO.class);
	}

	@Override
	public List<PaymentDTO> getPayementsByStatus(PaymentStatus paymentStatus) {
		// TODO Auto-generated method stub

		return paymentRepository.findByPayementStatus(paymentStatus).stream()
				.map((r) -> modelMapper.map(r, PaymentDTO.class)).toList();
	}

}
