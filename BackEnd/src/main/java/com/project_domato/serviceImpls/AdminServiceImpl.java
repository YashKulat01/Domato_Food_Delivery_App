/**
 * 	AUTHOR:-> YASH KULAT;
 */
package com.project_domato.serviceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.exceptions.NotFoundException;
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

// GET ALL USER SECTION ----------------------------------------------------------

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream().map((r) -> modelMapper.map(r, UserDTO.class)).toList();
	}

// GET USER BY ROLE SECTION ----------------------------------------------------------

	@Override
	public List<UserDTO> getUsersByRoles(Role role) {
		// TODO Auto-generated method stub

		return userRepository.getUsersByRole(role).stream().map((r) -> modelMapper.map(r, UserDTO.class)).toList();
	}


// DELETE USER BY EMAIL SECTION -----------------------------------------------------------------

	@Override
	public void removeUser(String email) {
		// TODO Auto-generated method stub

		User removedUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("User not Found !"));

		userRepository.delete(removedUser);

	}

// GET USER BY EMAIL SECTION -----------------------------------------------------

	@Override
	public UserDTO findByEmail(String email) {
		// TODO Auto-generated method stub

		User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not Found"));

		return modelMapper.map(user, UserDTO.class);
	}

}
