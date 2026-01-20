package com.project_domato.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecotrack.exceptions.NotFoundException;
import com.project_domato.Entities.Cart;
import com.project_domato.Entities.CartItems;
import com.project_domato.Entities.Food;
import com.project_domato.Entities.User;
import com.project_domato.dtos.CartDTO;
import com.project_domato.repositories.CartRepository;
import com.project_domato.repositories.FoodRepository;
import com.project_domato.repositories.UserRepository;
import com.project_domato.services.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CartDTO addToCart(Integer userId, Integer foodId, Integer quantity) {
		// TODO Auto-generated method stub

		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found !"));

		Food food = foodRepository.findById(foodId).orElseThrow(() -> new NotFoundException("Food not found !"));

		Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setUser(user);
			return newCart;
		});

		Optional<CartItems> presentItem = cart.getItems().stream().filter(i -> i.getFood().getFoodId().equals(foodId))
				.findFirst();

		if (presentItem.isPresent()) {
			CartItems items = presentItem.get();
			items.setQuantity(items.getQuantity() + quantity);
			items.setPrice(items.getQuantity() * food.getFoodPrice());
		} else {
			CartItems items = new CartItems();
			items.setCart(cart);
			items.setFood(food);
			items.setQuantity(quantity);
			items.setPrice(quantity * food.getFoodPrice());
			cart.getItems().add(items);
		}

		cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItems::getPrice).sum());

		Cart savedCart = cartRepository.save(cart);

		return modelMapper.map(savedCart, CartDTO.class);
	}

	@Override
	public CartDTO getCartByUserId(Integer userId) {
		// TODO Auto-generated method stub

		Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("Cart Not Found !"));

		return modelMapper.map(cart, CartDTO.class);
	}

	@Override
	public CartDTO removeFromCart(Integer userId, Integer foodId) {
		// TODO Auto-generated method stub

		Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("Cart not Found !"));

		cart.getItems().removeIf(i -> i.getFood().getFoodId().equals(foodId));

		cart.setTotalPrice(cart.getItems().stream().mapToDouble(CartItems::getPrice).sum());

		Cart savedCart = cartRepository.save(cart);

		return modelMapper.map(savedCart, CartDTO.class);
	}

	@Override
	public void deleteCart(Integer userId) {
		// TODO Auto-generated method stub

		Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("Cart Not Found"));

		cart.getItems().clear();
		cart.setTotalPrice(0.0);
		cartRepository.save(cart);

	}

	@Override
	public List<CartDTO> getAllCarts() {
		// TODO Auto-generated method stub
		
		List<Cart> allCarts = cartRepository.findAll();
		
		if (allCarts.isEmpty()) {
			throw new NotFoundException("Cart Not Found !");
		}
		
		List<CartDTO> allSavedCarts = allCarts.stream().map((r)-> modelMapper.map(r, CartDTO.class)).toList();
		
		return allSavedCarts;
	}

}
