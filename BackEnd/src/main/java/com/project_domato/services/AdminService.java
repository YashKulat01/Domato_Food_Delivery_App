/**
 * 	AUTHOR:-> YASH KULAT.
 */
package com.project_domato.services;

import java.util.List;

import com.project_domato.Entities.Role;
import com.project_domato.dtos.UserDTO;

/**
 * ADMIN SERVICE INTERFACE
 */
public interface AdminService {

	UserDTO addAdmin(UserDTO userDTO);

	void deleteAdmin(String email);

	List<UserDTO> getAllUsers();

	List<UserDTO> getUsersByRoles(Role role);

	void removeUser(String email);

	UserDTO findByEmail(String email);

}
