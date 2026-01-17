/**
 *  AUTHOR:-> YASH KULAT;
 */
package com.project_domato.dtos;

import com.project_domato.Entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  THE USERDTO CLASS;
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
	private String name;
	private String email;
	private String password;
	private String phoneNo;
	private Role role;
}
