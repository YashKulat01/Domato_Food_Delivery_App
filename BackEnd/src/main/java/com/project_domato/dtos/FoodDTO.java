/**
 * 	AUTHOR: YASH KULAT.
 */
package com.project_domato.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 	THIS CLASS IS DATA TRANSFER OBJECT FOR FOOD ENTITY.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FoodDTO {
	
	private String foodId;
	private String foodName;
	private String image;
	private Double price;
	private String description;
}
