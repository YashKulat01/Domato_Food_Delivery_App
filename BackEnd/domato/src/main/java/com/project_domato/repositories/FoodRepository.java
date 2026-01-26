package com.project_domato.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Food;
import com.project_domato.enums.FoodCategory;


public interface FoodRepository extends JpaRepository<Food, Integer>{

	List<Food> getFoodByCategory(FoodCategory foodCategory);
	
	Optional<Food>findByFoodName(String foodName);
}
