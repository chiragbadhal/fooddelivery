package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Food;
import com.learning.dto.FoodType;
import com.learning.service.FoodService;

@RestController

@RequestMapping("/api/request")
public class FoodController {

	@Autowired
	FoodService foodService;
	
	@PostMapping("/food")
	
	public ResponseEntity<?> addFood(@Valid @RequestBody Food food){
		
		Food result = foodService.addFood(food);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("/{foodId}")
	
	public ResponseEntity<?> getFoodById(@PathVariable("foodId") Long id){
		
		Food result = foodService.getFoodById(id);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("/food")
	
	public ResponseEntity<?> getAllFood(){
		Optional<List<Food>> optional = foodService.getAllFood();
		
		if(optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "no records found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PutMapping("/{foodId}")
	
	public ResponseEntity<?> updateFood(@PathVariable("/foodId") Long id, @RequestBody Food food){
		
		return new ResponseEntity<>(foodService.updateFood(id, food),HttpStatus.OK);
	}
	
	@DeleteMapping("/{foodId}")
	
	public ResponseEntity<?> deleteFood(@PathVariable("foodId") Long id){
		
		String result = foodService.deleteFoodById(id);
		
		if(result.equals("success")) {
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
