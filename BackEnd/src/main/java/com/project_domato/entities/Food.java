/**
 *	AUTHOR :-> YASH KULAT
 */
package com.project_domato.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * THIS IS A FOOD ENTITY CONTAINS INFORMATION RELATED TO THE FOOD.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foods")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String foodId;

	@Column
	private String foodName;

	@Column
	private String image;

	@Column
	private Double price;

	@Column(nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private FoodCategory category;

}
