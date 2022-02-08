package com.learning.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Food;
import com.learning.dto.EFOODTYPE;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
	
//	Optional<List<Food>> findByFoodType(FoodType foodtype);
}