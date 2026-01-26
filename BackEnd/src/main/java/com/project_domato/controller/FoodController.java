package com.project_domato.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.FoodDTO;
import com.project_domato.enums.FoodCategory;
import com.project_domato.services.FoodService;

@RestController
@RequestMapping("/food")

public class FoodController {

	@Autowired
	private FoodService foodService;
	
	// Food Section..................................................................
		// ADD FOOD SECTION
		// --------------------------------------------------------------
		@PostMapping("/add_food")
		public ResponseEntity<FoodDTO> addFood(@RequestBody FoodDTO foodDTO){
			
			return ResponseEntity.ok(foodService.addFood(foodDTO));
		}
		
		@GetMapping("/get_food/{Catg}")
		public ResponseEntity<List<FoodDTO>> getFoodByCategory(@PathVariable FoodCategory Catg){
			
			return ResponseEntity.ok(foodService.getFoodByCategory(Catg));
		}
		
		@GetMapping("/get_foods")
		public ResponseEntity<List<FoodDTO>> getAllFoods(){
			return ResponseEntity.ok(foodService.getAllFoods());
		}
		
		@DeleteMapping("/del_food/{name}")
		public ResponseEntity<Map<String, String>> deleteFood(@PathVariable String name) {

			foodService.deleteFood(name);

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("Message", "Food Deleted!");

			return ResponseEntity.ok(map);
		}
}
