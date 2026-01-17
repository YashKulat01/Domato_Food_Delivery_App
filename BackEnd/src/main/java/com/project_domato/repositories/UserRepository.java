/**
 *  AUTHOR:-> YASH KULAT;
 */
package com.project_domato.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Role;
import com.project_domato.Entities.User;

/**
 * THE USER REPOSITORY;
 */
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmail(String email);

	List<User> getUsersByRole(Role role);

}
