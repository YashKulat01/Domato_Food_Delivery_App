package com.project_domato.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByUserId(Integer userId);
}
