package com.project_domato.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_domato.Entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	List<Address> findByCity(String city);
	
	List<Address> findByState(String state);
}
