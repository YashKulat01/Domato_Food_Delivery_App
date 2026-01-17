/**
 *  AUTHOR:-> YASH KULAT.
 */
package com.project_domato.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * THIS ENTITY CONTAIN THE FOOD CATEGORIES AND RELATED INFO
 */

public class FoodCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "category")
	private List<Food> foods;
}
