/**
 * 	AUTHOR:-> YASH KULAT
 */
package com.project_domato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.entities.FoodCategory;

/**
 * THIS IS A USER REPOSITORY HANDLES DATABASE OPERATIONS LIKE SAVE, UPDATE,
 * DELETE, AND FETCH DATA FROM THE DATABASE.
 * 
 * ACT AS A BRIDGE BETWEEN THE SERVICE LAYER AND THE DATABASE.
 */
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer>{

}
