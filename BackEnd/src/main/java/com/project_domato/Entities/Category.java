/**
 * 	AUTHOR:-> YASH KULAT;
 */
package com.project_domato.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project_domato.enums.FoodCategory;

import jakarta.persistence.Column;
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
 * THIS IS CATEGORY CLASS;
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer C_id;

	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private FoodCategory foodCategory;

	@OneToMany(mappedBy = "category")
	@JsonBackReference
	private List<Food> foods;

}
