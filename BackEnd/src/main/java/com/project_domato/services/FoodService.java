package com.project_domato.services;

import java.util.List;

import com.project_domato.dtos.FoodDTO;
import com.project_domato.enums.FoodCategory;

public interface FoodService {

	FoodDTO addFood(FoodDTO foodDTO);

	List<FoodDTO> getFoodByCategory(FoodCategory foodCategory);

	List<FoodDTO> getAllFoods();

	void deleteFood(String foodName);
}
