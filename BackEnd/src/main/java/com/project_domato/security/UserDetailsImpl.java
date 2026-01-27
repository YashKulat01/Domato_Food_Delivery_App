package com.project_domato.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project_domato.Entities.User;
import com.project_domato.exception.NotFoundException;
import com.project_domato.repositories.UserRepository;

@Service

public class UserDetailsImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found !"));
		return user;
	}

}
