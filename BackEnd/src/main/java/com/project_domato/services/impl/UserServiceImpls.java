/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_domato.dtos.UserDTO;
import com.project_domato.entities.Role;
import com.project_domato.entities.User;
import com.project_domato.enums.AppRole;
import com.project_domato.repositories.RoleRepository;
import com.project_domato.repositories.UserRepository;
import com.project_domato.services.UserService;

/**
 *  IT HAS AN IMPLEMENTATIONS FOR THE ALL OF THE METHODS
 *  OF THE SERVICES INTERFACE.
 */

@Service
public class UserServiceImpls implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
		// CONVERTS THE USERDTO INTO USER ENTITIY AND RETURNS AN OBJ.
		User user = modelMapper.map(userDTO, User.class);
		
		// SETS THE ROLE TO THE USER
		Role role = roleRepository.findByRoleName(AppRole.ROLE_USER).orElseThrow(()-> new RuntimeException("Role Not Found !"));
		user.setRole(role);
		
		// SAVES THE CONVERSION PROCESS AND RETURN THE OBJ...
		User savedUser = userRepository.save(user);
		
		// CONVERT THE UPDATED ENTITY INTO DTO AGAIN..
		return modelMapper.map(savedUser, UserDTO.class);
	}
}
