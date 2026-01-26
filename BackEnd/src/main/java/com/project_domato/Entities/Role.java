/**
 * 	AUTHOR:-> YASH KULAT.
 */
package com.project_domato.Entities;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * THE ROLE ENTITIY CLASS;
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
