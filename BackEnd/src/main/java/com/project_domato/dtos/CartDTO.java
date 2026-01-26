package com.project_domato.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartDTO {
	private Long cartId;
    private Long userId;
    private List<CardItemDTO> items;
    private Double totalPrice;
}
