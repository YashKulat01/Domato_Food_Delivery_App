package com.project_domato.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project_domato.dtos.FoodDTO;
import com.project_domato.enums.FoodCategory;
import com.project_domato.services.FoodService;

@RestController
@RequestMapping("/food")

public class FoodController {

	@Value("${app.upload-dir:uploads}")
	private String uploadDir;

	@Autowired
	private FoodService foodService;

	@PostMapping("/add_food")
	public ResponseEntity<FoodDTO> addFood(@RequestBody FoodDTO foodDTO) {
		return ResponseEntity.ok(foodService.addFood(foodDTO));
	}

	@PostMapping(value = "/add_food_with_image", consumes = { "multipart/form-data" })
	public ResponseEntity<FoodDTO> addFoodWithImage(
			@RequestParam("image") MultipartFile image,
			@RequestParam("foodName") String foodName,
			@RequestParam("foodPrice") Double foodPrice,
			@RequestParam("foodDesc") String foodDesc,
			@RequestParam("foodCategory") FoodCategory foodCategory) throws IOException {

		String imagePath = null;
		if (image != null && !image.isEmpty()) {
			Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
			Files.createDirectories(uploadPath);
			String originalFilename = image.getOriginalFilename();
			String extension = originalFilename != null && originalFilename.contains(".")
					? originalFilename.substring(originalFilename.lastIndexOf("."))
					: "";
			String filename = "food_" + UUID.randomUUID().toString() + extension;
			Path filePath = uploadPath.resolve(filename);
			Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			imagePath = "/uploads/" + filename;
		}

		FoodDTO dto = new FoodDTO();
		dto.setFoodName(foodName);
		dto.setFoodImg(imagePath);
		dto.setFoodPrice(foodPrice);
		dto.setFoodDesc(foodDesc);
		dto.setFoodCategory(foodCategory);

		return ResponseEntity.ok(foodService.addFood(dto));
	}

	@GetMapping("/get_food/{Catg}")
	public ResponseEntity<List<FoodDTO>> getFoodByCategory(@PathVariable FoodCategory Catg) {
		return ResponseEntity.ok(foodService.getFoodByCategory(Catg));
	}

	@GetMapping("/get_foods")
	public ResponseEntity<List<FoodDTO>> getAllFoods() {
		return ResponseEntity.ok(foodService.getAllFoods());
	}

	@DeleteMapping("/del_food/{name}")
	public ResponseEntity<Map<String, String>> deleteFood(@PathVariable String name) {
		foodService.deleteFood(name);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Message", "Food Deleted!");
		return ResponseEntity.ok(map);
	}
}
