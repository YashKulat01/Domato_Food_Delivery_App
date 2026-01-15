/**
 * AUTHOR:-> YASH KULAT
 */
package com.project_domato.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.entities.Role;
import com.project_domato.enums.AppRole;

/**
 * 	THIS IS A USER REPOSITORY HANDLES DATABASE OPERATIONS LIKE SAVE, UPDATE,
 * 	DELETE, AND FETCH DATA FROM THE DATABASE.
 * 
 * 	ACT AS A BRIDGE BETWEEN THE SERVICE LAYER AND THE DATABASE.
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{

 	Optional<Role> findByRoleName(AppRole roleName);
}
