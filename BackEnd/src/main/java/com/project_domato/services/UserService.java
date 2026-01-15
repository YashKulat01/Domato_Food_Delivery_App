/**
 * 	AUTHOR:-> YASH KULAT.
 */
package com.project_domato.services;

import com.project_domato.dtos.UserDTO;

/**
 * 	A SERVICE IS A COMPONENT THAT CONTAINS BUSINESS LOGICS AND 
 * 	COORDINATES BETWEEN CONTROLLERS AND REPOSITORIES.
 */
public interface UserService {
	
	// REGISTER USER SERVICE...
	UserDTO registerUser(UserDTO userDTO);
}
