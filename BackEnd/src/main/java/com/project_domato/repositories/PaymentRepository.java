package com.project_domato.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Payment;
import com.project_domato.enums.PaymentStatus;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Optional<Payment> findByOrderId(Integer orderId);
	
	List<Payment> findByPayementStatus(PaymentStatus payementStatus);
}
