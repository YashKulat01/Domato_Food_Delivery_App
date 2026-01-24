package com.project_domato.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_domato.dtos.AddressDTO;
import com.project_domato.services.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("/add")
	public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {

		AddressDTO address = addressService.addAddress(addressDTO);

		return new ResponseEntity<AddressDTO>(address, HttpStatus.CREATED);
	}

	@PostMapping("/add/{id}")
	public ResponseEntity<AddressDTO> addAddress(@PathVariable Integer id, @RequestBody AddressDTO addressDTO) {

		AddressDTO address = addressService.addAddressToOrder(id, addressDTO);

		return new ResponseEntity<AddressDTO>(address, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressDTO> getAddressById(@PathVariable Integer id) {

		return ResponseEntity.ok(addressService.getAddressById(id));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AddressDTO>> getAllAddress() {

		return ResponseEntity.ok(addressService.getAllAddresses());
	}
	
	@DeleteMapping("/del_addrs/{id}")
	public ResponseEntity<Map<String, String>> deleteAddress(@PathVariable Integer id) {
		addressService.deleteAddress(id);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("Message", "Address Deleted!");

		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/get_addrs/{city}")
	public ResponseEntity<List<AddressDTO>> getAddressByCity(@PathVariable String city) {

		return ResponseEntity.ok(addressService.getAddressByCity(city));
	}
	
	@GetMapping("/get_addrs_by/{state}")
	public ResponseEntity<List<AddressDTO>> getAddressByState(@PathVariable String state) {

		return ResponseEntity.ok(addressService.getAddressByState(state));
	}
	
}
