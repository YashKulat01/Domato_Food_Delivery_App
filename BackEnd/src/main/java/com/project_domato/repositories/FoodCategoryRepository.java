/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Category;
import com.project_domato.Entities.Food;
import java.util.List;


/**
 * 	FOOD CATEGORY INTERFACE
 */
public interface FoodCategoryRepository extends JpaRepository<Food, Integer>{

	List<Food> findByCategory(Category category);
}
