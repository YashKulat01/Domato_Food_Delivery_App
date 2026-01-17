/**
 * 
 */
package com.project_domato.services;

import java.util.List;

import com.project_domato.entities.FoodCategory;

/**
 * 
 */
public interface FoodCategoryService {

	FoodCategory createCategory(FoodCategory category);

	List<FoodCategory> getAllCategories();

	FoodCategory getCategoryById(Long id);
}
