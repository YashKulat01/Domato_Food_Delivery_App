/**
 * 	AUTHOR:-> YASH KULAT;
 */
package com.project_domato.services;

import java.util.List;

import com.project_domato.Entities.Role;
import com.project_domato.dtos.UserDTO;

/**
 * USER SERVICE INTERFACE
 */
public interface UserService {

	UserDTO registerUser(UserDTO userDTO);

	List<UserDTO> getAllUsers();

	List<UserDTO> getUsersByRoles(Role role);
	
	void removeUser(String email);

	UserDTO findByEmail(String email);

}
