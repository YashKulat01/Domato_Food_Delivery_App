/**
 * 	AUTHOR:-> YASH KULAT;
 */
package com.project_domato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.FoodDTO;
import com.project_domato.services.FoodService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 	A CONTROLLER IS A COMPONENT THAT RECEIVES CLIENT REQUESTS, DELEGATES BUSINESS
 * 	LOGIC TO SERVICES, AND RETURNS RESPONSES;
 */

@RestController
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@PostMapping("/add")
	public ResponseEntity<FoodDTO> addFood(@RequestBody FoodDTO foodDTO) {
		//TODO: process POST request
		
		FoodDTO addedFood = foodService.addFood(foodDTO);
		
		return new ResponseEntity<FoodDTO>(addedFood, HttpStatus.CREATED);
	}
	
	
}
