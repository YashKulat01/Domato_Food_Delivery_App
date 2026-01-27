package com.project_domato.security;

import com.project_domato.dtos.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponse {

	private UserDTO userDto;
	
	private String token;
}
