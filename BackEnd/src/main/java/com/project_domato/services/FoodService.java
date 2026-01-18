/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.services;

import java.util.List;

import com.project_domato.dtos.FoodDTO;
import com.project_domato.enums.FoodCategory;

/**	
 * 	FOOD SERVICE INTERFACE
 */
public interface FoodService {
	
	FoodDTO addFood(FoodDTO foodDTO);
	
	void removeFood(int id);
	
	List<FoodDTO> getFoodByCategory(FoodCategory foodCategory);
	
}
