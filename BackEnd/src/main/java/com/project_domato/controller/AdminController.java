/**
 * 	AUTHOR:-> YASH KULAT.
 */
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

import com.project_domato.Entities.Role;
import com.project_domato.dtos.FoodDTO;
import com.project_domato.dtos.UserDTO;
import com.project_domato.enums.FoodCategory;
import com.project_domato.services.AdminService;
import com.project_domato.services.FoodService;
import com.project_domato.services.UserService;

/**
 * ADMIN CONTROLLER CLASS;
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

//	@Autowired
//	private CategoryService categoryService;
	
	@Autowired
	private FoodService foodService;

	// ADD ADMIN SECTION
	// ------------------------------------------------------------
	@PostMapping("/add_admin")
	public ResponseEntity<UserDTO> addAdmin(@RequestBody UserDTO userDTO) {

		UserDTO addedAdmin = adminService.addAdmin(userDTO);
		return new ResponseEntity<UserDTO>(addedAdmin, HttpStatus.CREATED);
	}

	// REMOVE ADMIN SECTION
	// ------------------------------------------------------------------

	@DeleteMapping("/del_admin/{email}")
	public ResponseEntity<Map<String, String>> deleteAdmin(@PathVariable String email) {
		adminService.deleteAdmin(email);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("Message", "Admin Deleted!");

		return ResponseEntity.ok(map);
	}

	// GET ALL USER SECTION
	// -----------------------------------------------------------

	@GetMapping("/all_users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {

		return ResponseEntity.ok(userService.getAllUsers());
	}

	// GET USER BY ROLE SECTION
	// ----------------------------------------------------------

	@GetMapping("/role/{role}")
	public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable Role role) {

		return ResponseEntity.ok(userService.getUsersByRoles(role));
	}

	// DELETE USER BY EMAIL SECTION
	// -----------------------------------------------------------------

	@DeleteMapping("del_user/{email}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String email) {

		userService.removeUser(email);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("Message", "User Deleted!");

		return ResponseEntity.ok(map);
	}

	// GET USER BY EMAIL SECTION
	// -----------------------------------------------------

	@GetMapping("/get_user/{email}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable String email) {

		return ResponseEntity.ok(userService.findByEmail(email));
	}


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
