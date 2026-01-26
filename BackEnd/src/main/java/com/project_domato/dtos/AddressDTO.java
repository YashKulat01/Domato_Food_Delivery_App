package com.project_domato.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	private String email;

	private String street;

	private String city;

	private String state;

	private Integer zipCode;

	private String Country;

	private Integer phone;
}
