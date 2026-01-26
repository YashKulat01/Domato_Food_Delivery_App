package com.project_domato.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.CategoryDTO;
import com.project_domato.enums.FoodCategory;
import com.project_domato.services.CategoryService;

@RestController
@RequestMapping("/admin")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add_cate")
	public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
		// TODO: process POST request

		CategoryDTO addedCategory = categoryService.addCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(addedCategory, HttpStatus.CREATED);
	}

	@GetMapping("/catgs")
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		return ResponseEntity.ok(categoryService.findAllCategory());
	}

	@DeleteMapping("del_catg/{foodCategory}")
	public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable FoodCategory foodCategory) {

		categoryService.deleteCategory(foodCategory);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("Message", "Category Deleted!");

		return ResponseEntity.ok(map);
	}

}
