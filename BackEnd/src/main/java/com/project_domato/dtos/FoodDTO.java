/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.dtos;

import com.project_domato.enums.FoodCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DATA TRANSFER OBJECT OF THE FOOD ENTITY
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FoodDTO {

	private Integer id;
	private String name;
	private String image;
	private Double price;
	private String description;

	private Integer C_id;
	private FoodCategory foodCategory;

}
