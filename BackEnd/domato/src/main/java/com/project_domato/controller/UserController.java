/**
 * 	AUTHOR:-> YASH KULAT;
 */
package com.project_domato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.UserDTO;
import com.project_domato.services.UserService;

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

}
