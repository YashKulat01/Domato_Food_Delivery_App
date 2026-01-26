package com.project_domato.dtos;

import java.time.LocalDateTime;

import com.project_domato.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentDTO {

	private Integer id;
	
	private String method;
	
	private PaymentStatus payementStatus;
	
	private Integer orderId;
	
	private Double amount;
	
	private LocalDateTime localDateTime;
}
