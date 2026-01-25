package com.project_domato.Entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project_domato.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")

public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Double totalAmount;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	private LocalDateTime orderDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderItems> orderItems;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@JsonManagedReference
	private Address address;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "payment_id", referencedColumnName = "id")
	@JsonManagedReference
	private Payment payment;

}
