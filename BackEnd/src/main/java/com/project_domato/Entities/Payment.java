package com.project_domato.Entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project_domato.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus payementStatus;
	
	private Double amount;
	
	private String method;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Order order;
	
	private LocalDateTime paymentDate;
}
