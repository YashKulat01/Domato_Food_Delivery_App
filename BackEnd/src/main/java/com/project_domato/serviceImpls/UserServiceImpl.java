/**
 *  AUTHOR:-> YASH KULAT;
 */
package com.project_domato.serviceImpls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_domato.Entities.Role;
import com.project_domato.Entities.User;
import com.project_domato.dtos.UserDTO;
import com.project_domato.enums.AppRole;
import com.project_domato.repositories.RoleRepository;
import com.project_domato.repositories.UserRepository;
import com.project_domato.services.UserService;

/**
 * USER SERVICE IMPLEMENTATION CLASS;
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

// REGISTER USER SECTION----------------------------------------------------------

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		// TODO Auto-generated method stub

		User user = modelMapper.map(userDTO, User.class);

		Role role = roleRepository.findByRoleName(AppRole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Role Not Found !!"));

		user.setRole(role);

		User savedUser = userRepository.save(user);

		return modelMapper.map(savedUser, UserDTO.class);
	}

}
