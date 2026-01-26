package com.project_domato.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Category;
import com.project_domato.enums.FoodCategory;


public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Optional<Category>  findByFoodCategory(FoodCategory foodCategory);
}
