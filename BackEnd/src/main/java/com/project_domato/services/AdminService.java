/**
 * 	AUTHOR:-> YASH KULAT.
 */
package com.project_domato.services;

import com.project_domato.dtos.UserDTO;

/**
 * ADMIN SERVICE INTERFACE
 */
public interface AdminService {

	UserDTO addAdmin(UserDTO userDTO);

	void deleteAdmin(String email);

}
