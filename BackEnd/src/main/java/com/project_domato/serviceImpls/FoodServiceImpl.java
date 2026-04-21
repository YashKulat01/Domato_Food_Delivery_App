package com.project_domato.serviceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project_domato.exception.NotFoundException;
import com.project_domato.Entities.Category;
import com.project_domato.Entities.Food;
import com.project_domato.dtos.FoodDTO;
import com.project_domato.enums.FoodCategory;
import com.project_domato.repositories.CategoryRepository;
import com.project_domato.repositories.FoodRepository;
import com.project_domato.services.FoodService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PersistenceContext
	private EntityManager entityManager;

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
		return foodRepository.getFoodByCategoryAndDeletedFalse(foodCategory).stream().map((r) -> modelMapper.map(r, FoodDTO.class))
				.toList();
	}

	@Override
	public List<FoodDTO> getAllFoods() {
		// TODO Auto-generated method stub
		return foodRepository.findByDeletedFalse().stream().map((r) -> modelMapper.map(r, FoodDTO.class)).toList();
	}

	@Override
	@Transactional
	public void deleteFood(String foodName) {
		// TODO Auto-generated method stub

		Food removedFood = foodRepository.findByFoodNameAndDeletedFalse(foodName)
				.orElseThrow(() -> new NotFoundException("Food not Found !"));
		
		// Keep order history by detaching order-item rows from this food.
		entityManager.createQuery("update OrderItems oi set oi.food = null where oi.food.foodId = :foodId")
				.setParameter("foodId", removedFood.getFoodId())
				.executeUpdate();
		
		// Clean cart rows that still reference this food.
		entityManager.createQuery("delete from CartItems ci where ci.food.foodId = :foodId")
				.setParameter("foodId", removedFood.getFoodId())
				.executeUpdate();
		
		// Hard delete from database.
		foodRepository.delete(removedFood);
	}

}
