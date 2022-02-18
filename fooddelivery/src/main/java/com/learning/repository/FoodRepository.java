package com.learning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Food;
import com.learning.dto.FoodType;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> 
{

	boolean existsByFoodName(String foodName);

	boolean existsById(Long id);
	
	
//	Optional<List<Food>> findByFoodType(FoodType foodtype);
}

  