package com.project_domato.dtos;

import com.project_domato.enums.FoodCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FoodDTO {
	
	private Integer foodId;
	private String foodName;
	private String foodImg;
	private Double foodPrice;
	private String foodDesc;
	private FoodCategory foodCategory;
}
