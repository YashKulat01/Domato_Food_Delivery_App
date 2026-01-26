/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.services;

import java.util.List;

import com.project_domato.dtos.CategoryDTO;
import com.project_domato.enums.FoodCategory;

/**
 * CATEGORY SERVICE INTERFACE
 */
public interface CategoryService {

	CategoryDTO addCategory(CategoryDTO categoryDTO);
	
	List<CategoryDTO> findAllCategory();
	
	void deleteCategory(FoodCategory foodCategory);
}
