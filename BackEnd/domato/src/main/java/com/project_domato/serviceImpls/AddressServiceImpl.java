package com.project_domato.serviceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_domato.Entities.Address;
import com.project_domato.Entities.Order;
import com.project_domato.dtos.AddressDTO;
import com.project_domato.exception.NotFoundException;
import com.project_domato.repositories.AddressRepository;
import com.project_domato.repositories.OrderRepository;
import com.project_domato.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AddressDTO addAddress(AddressDTO addressDTO) {
		// TODO Auto-generated method stub
		Address address = modelMapper.map(addressDTO, Address.class);
		Address savedAddress = addressRepository.save(address);
		return modelMapper.map(savedAddress, AddressDTO.class);
	}

	@Override
	public AddressDTO addAddressToOrder(Integer orderId, AddressDTO addressDTO) {
		// TODO Auto-generated method stub

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not Found !"));

		Address address = modelMapper.map(addressDTO, Address.class);

		address.setOrder(order);
		order.setAddress(address);

		Address savedAddress = addressRepository.save(address);

		orderRepository.save(order);

		return modelMapper.map(savedAddress, AddressDTO.class);
	}

	@Override
	public AddressDTO getAddressById(Integer id) {
		// TODO Auto-generated method stub
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Address not Found !"));

		return modelMapper.map(address, AddressDTO.class);
	}

	@Override
	public List<AddressDTO> getAllAddresses() {
		// TODO Auto-generated method stub
		return addressRepository.findAll().stream().map(address -> modelMapper.map(address, AddressDTO.class)).toList();
	}

	@Override
	public void deleteAddress(Integer id) {
		// TODO Auto-generated method stub
		addressRepository.deleteById(id);
	}

	@Override
	public List<AddressDTO> getAddressByCity(String cityString) {
		// TODO Auto-generated method stub

		return addressRepository.findByCity(cityString).stream()
				.map(address -> modelMapper.map(address, AddressDTO.class)).toList();
	}

	@Override
	public List<AddressDTO> getAddressByState(String stateString) {
		// TODO Auto-generated method stub
		return addressRepository.findByState(stateString).stream()
				.map(address -> modelMapper.map(address, AddressDTO.class)).toList();
	}

}
