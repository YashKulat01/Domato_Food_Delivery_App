/**
 * 	AUTHOR:-> YASH KULAT.
 */
package com.project_domato.services;

import java.util.List;

import com.project_domato.dtos.FoodDTO;
import com.project_domato.entities.Food;

/**
 * 	A SERVICE IS A COMPONENT THAT CONTAINS BUSINESS LOGICS AND COORDINATES
 * 	BETWEEN CONTROLLERS AND REPOSITORIES.
 */
public interface FoodService {
	
	FoodDTO addFood(FoodDTO foodDTO);
	
	List<Food> getAllFoods();

    Food getFoodById(Long id);
}
