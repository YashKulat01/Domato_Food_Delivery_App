/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project_domato.enums.AppRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * THIS ENTITIES CONTAIN THE INFORMATION RELATED TO THE ROLES.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")

public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private AppRole roleName;

	@OneToMany(mappedBy = "role")
	@JsonBackReference
	private List<User> users;
}
