/**
 * 
 */
package com.project_domato.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Role;
import com.project_domato.enums.AppRole;

/**
 * 
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByRoleName(AppRole appRole);
}
