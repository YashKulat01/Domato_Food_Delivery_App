package com.project_domato.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	Optional<Cart> findByUserId(Integer userId);
}
