package com.project_domato.Entities;

import com.project_domato.enums.FoodCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer foodId;
	
	@Column(nullable = false,unique = true)
	private String foodName;
	
	private String foodImg;
	
	@Column(nullable = false)
	private Double foodPrice;
	
	@Column(nullable = false)
	private String foodDesc;
	
	@Enumerated(EnumType.STRING)
	private FoodCategory foodCategory;
}
