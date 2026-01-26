package com.project_domato.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CardItemDTO {
	private Long foodId;
	private String foodName;
	private Double foodPrice;
	private Integer quantity;

	public Double getTotalItemPrice() {
		return foodPrice * quantity;
	}
}
