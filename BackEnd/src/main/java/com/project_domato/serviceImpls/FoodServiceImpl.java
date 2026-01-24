package com.project_domato.serviceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_domato.exception.NotFoundException;
import com.project_domato.Entities.Category;
import com.project_domato.Entities.Food;
import com.project_domato.dtos.FoodDTO;
import com.project_domato.enums.FoodCategory;
import com.project_domato.repositories.CategoryRepository;
import com.project_domato.repositories.FoodRepository;
import com.project_domato.services.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public FoodDTO addFood(FoodDTO foodDTO) {
		// TODO Auto-generated method stub

		Food food = modelMapper.map(foodDTO, Food.class);

		Category foodCategory = categoryRepository.findByFoodCategory(foodDTO.getFoodCategory()).orElseThrow(() -> new RuntimeException("Category not found"));
		food.setCategory(foodCategory);
		
		Food savedFood = foodRepository.save(food);

		return modelMapper.map(savedFood, FoodDTO.class);
	}

	@Override
	public List<FoodDTO> getFoodByCategory(FoodCategory foodCategory) {
		// TODO Auto-generated method stub
		return foodRepository.getFoodByCategory(foodCategory).stream().map((r) -> modelMapper.map(r, FoodDTO.class))
				.toList();
	}

	@Override
	public List<FoodDTO> getAllFoods() {
		// TODO Auto-generated method stub
		return foodRepository.findAll().stream().map((r) -> modelMapper.map(r, FoodDTO.class)).toList();
	}

	@Override
	public void deleteFood(String foodName) {
		// TODO Auto-generated method stub

		Food removedFood = foodRepository.findByFoodName(foodName)
				.orElseThrow(() -> new NotFoundException("Food not Found !"));

		foodRepository.delete(removedFood);
	}

}
