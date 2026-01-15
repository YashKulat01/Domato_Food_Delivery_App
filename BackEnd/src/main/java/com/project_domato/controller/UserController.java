/**
 * 	AUTHOR:-> YASH KULAT;
 */
package com.project_domato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.UserDTO;
import com.project_domato.services.UserService;

/**
 * 
 * A CONTROLLER IS A COMPONENT THAT RECEIVES CLIENT REQUESTS, DELEGATES BUSINESS
 * LOGIC TO SERVICES, AND RETURNS RESPONSES;
 * 
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {

		// REQUEST BODY READS THE PARAMETERS

		// REGISTER USER METHOD REGISTER THE USER AND RETURNS THE OBJ.
		UserDTO registeredUser = userService.registerUser(userDTO);

		return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.CREATED);

		/*
		 * API TESTING LINK:-> http://localhost:8080/user/register
		 */
	}

}
