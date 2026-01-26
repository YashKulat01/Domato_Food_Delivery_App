/**
 *  AUTHOR:-> YASH KULAT;
 */
package com.project_domato.dtos;

import java.util.List;

import com.project_domato.Entities.Cart;
import com.project_domato.Entities.Order;
import com.project_domato.Entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * THE USERDTO CLASS;
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
	private Cart cart;
	private List<Order> orders;
}
