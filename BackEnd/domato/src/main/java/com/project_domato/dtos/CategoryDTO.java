/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.dtos;

import com.project_domato.enums.FoodCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 	DATA TRANSFER OBJECT OF CATEGORY ENTITY
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDTO {

	private Integer C_id;
	
	private FoodCategory foodCategory;
}
