/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Category;
import java.util.Optional;
import com.project_domato.enums.FoodCategory;



/**
 * 	CATEGORY REPOSITORY INTERFACE
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	 Optional<Category> findByFoodCategory(FoodCategory foodCategory);;
}
