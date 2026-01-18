/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.serviceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.exceptions.NotFoundException;
import com.project_domato.Entities.Category;
import com.project_domato.dtos.CategoryDTO;
import com.project_domato.enums.FoodCategory;
import com.project_domato.repositories.CategoryRepository;
import com.project_domato.services.CategoryService;

/**
 * CATEGORY SERVICE IMPLEMENTATION CLASS
 */

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub

		Category category = modelMapper.map(categoryDTO, Category.class);

		Category savedCategory = categoryRepository.save(category);

		return modelMapper.map(savedCategory, CategoryDTO.class);

	}

	@Override
	public List<CategoryDTO> findAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll().stream().map((r) -> modelMapper.map(r, CategoryDTO.class)).toList();
	}

	@Override
	public void deleteCategory(FoodCategory foodCategory) {
		// TODO Auto-generated method stub
		Category removedCategory = categoryRepository.findByFoodCategory(foodCategory)
				.orElseThrow(() -> new NotFoundException("Category not found"));

		categoryRepository.delete(removedCategory);
	}

}
