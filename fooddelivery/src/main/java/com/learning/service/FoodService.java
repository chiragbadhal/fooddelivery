package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.dto.Food;
import com.learning.dto.EFOODTYPE;
import com.learning.exception.IdNotFoundException;

public interface FoodService {

	public Food addFood(Food food);
	public Optional<List<Food>> getAllFood();
	public Food getFoodById(int id);
	public String updateFood(int id, Food food);
	public String deleteFoodById(int id);
}	