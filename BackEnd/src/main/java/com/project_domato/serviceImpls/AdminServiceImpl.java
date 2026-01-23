/**
 * 	AUTHOR:-> YASH KULAT;
 */
package com.project_domato.serviceImpls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_domato.exceptions.NotFoundException;
import com.project_domato.Entities.Role;
import com.project_domato.Entities.User;
import com.project_domato.dtos.UserDTO;
import com.project_domato.enums.AppRole;
import com.project_domato.repositories.RoleRepository;
import com.project_domato.repositories.UserRepository;
import com.project_domato.services.AdminService;

/**
 * ADMIN SERVICE IMPLEMENTAION CLASS
 */

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

// ADD ADMIN SECTION..............................................
	
	@Override
	public UserDTO addAdmin(UserDTO userDTO) {
		// TODO Auto-generated method stub

		User user = modelMapper.map(userDTO, User.class);

		Role role = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Role not Found !"));

		user.setRole(role);

		User savedUser = userRepository.save(user);

		return modelMapper.map(savedUser, UserDTO.class);
	}

// DELETE ADMIN SECTION........................................
	
	@Override
	public void deleteAdmin(String email) {
		// TODO Auto-generated method stub

		User removedAdmin = userRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Admin not Found !"));

		userRepository.delete(removedAdmin);
	}



}
