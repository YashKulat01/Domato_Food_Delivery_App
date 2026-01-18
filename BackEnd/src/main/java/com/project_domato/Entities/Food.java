/**
 *  AUTHOR:-> YASH KULAT;
 */
package com.project_domato.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 	USER ENTITY CLASS
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	private String image;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToOne
	@JsonBackReference
	private Category category;
}
