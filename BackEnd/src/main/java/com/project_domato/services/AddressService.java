package com.project_domato.services;

import java.util.List;

import com.project_domato.dtos.AddressDTO;

public interface AddressService {

	AddressDTO addAddress(AddressDTO addressDTO);
	AddressDTO addAddressToOrder(Integer orderId,AddressDTO addressDTO);
	AddressDTO getAddressById(Integer id);
	List<AddressDTO> getAllAddresses();
    void deleteAddress(Integer id);
    
    List<AddressDTO> getAddressByCity(String cityString);
    List<AddressDTO> getAddressByState(String stateString);
    
}
