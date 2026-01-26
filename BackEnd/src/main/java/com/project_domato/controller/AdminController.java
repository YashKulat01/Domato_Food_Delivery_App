/**
 * 	AUTHOR:-> YASH KULAT.
 */
package com.project_domato.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.UserDTO;
import com.project_domato.services.AdminService;

/**
 * ADMIN CONTROLLER CLASS;
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

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

}
