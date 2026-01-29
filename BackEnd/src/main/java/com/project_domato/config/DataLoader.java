package com.project_domato.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project_domato.Entities.Category;
import com.project_domato.Entities.Role;
import com.project_domato.Entities.User;
import com.project_domato.enums.AppRole;
import com.project_domato.enums.FoodCategory;
import com.project_domato.repositories.CategoryRepository;
import com.project_domato.repositories.RoleRepository;
import com.project_domato.repositories.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public DataLoader(CategoryRepository categoryRepository, RoleRepository roleRepository,
			UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.categoryRepository = categoryRepository;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {
		for (FoodCategory fc : FoodCategory.values()) {
			categoryRepository.findByFoodCategory(fc).orElseGet(() -> {
				Category c = new Category();
				c.setFoodCategory(fc);
				return categoryRepository.save(c);
			});
		}
		for (AppRole ar : AppRole.values()) {
			roleRepository.findByRoleName(ar).orElseGet(() -> {
				Role r = new Role();
				r.setRoleName(ar);
				return roleRepository.save(r);
			});
		}
		String adminEmail = "admin@domato.com";
		if (userRepository.findByEmail(adminEmail).isEmpty()) {
			Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN).orElseThrow();
			User admin = new User();
			admin.setName("Admin");
			admin.setEmail(adminEmail);
			admin.setPassword(passwordEncoder.encode("Admin@123"));
			admin.setPhoneNo("0000000000");
			admin.setRole(adminRole);
			userRepository.save(admin);
		}
	}
}
