/**
 * 	AUTHOR:-> YASH KULAT.
 */
package com.project_domato.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * THE USER ENTITY CLASS.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 40, nullable = false) //
	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(length = 16, nullable = false)
	private String password;

	@Column(length = 10, nullable = false)
	private String phoneNo;

	@ManyToOne
	@JsonManagedReference
	private Role role;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Cart cart;
}
