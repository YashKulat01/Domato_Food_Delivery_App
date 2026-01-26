/**
 * 	AUTHOR:-> YASH KULAT;
 */
package com.project_domato.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.Entities.Role;
import com.project_domato.dtos.UserDTO;
import com.project_domato.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * USERCONTROLLER CLASS;
 */

@RestController
@RequestMapping("/users")

public class UserController {

	@Autowired
	private UserService userService;

// REGISTER USER SECTION----------------------------------------------------------

	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
		// TODO: process POST request

		UserDTO registeredUser = userService.registerUser(userDTO);
		return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.CREATED);
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


}
