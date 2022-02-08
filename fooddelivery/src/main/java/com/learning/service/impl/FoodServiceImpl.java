package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.EFOODTYPE;
import com.learning.repo.FoodRepository;
import com.learning.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	@Override
	public Food addFood(Food food) {
		// TODO Auto-generated method stub
		Food food2 = foodRepository.save(food);
		if(food2!=null) {
			return food2;
		}
		else
			return null;
	}

	@Override
	public Optional<List<Food>> getAllFood() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(foodRepository.findAll());
	}

	@Override
	public Food getFoodById(int id) {
		// TODO Auto-generated method stub
		Optional<Food> optional = foodRepository.findById(id);
		if(optional.isEmpty())
			return null;
		else
			return optional.get();
	}

	@Override
	public String updateFood(int id, Food food) {
		// TODO Auto-generated method stub
		if(this.foodRepository.existsById(id)==false)
			return "fail";
		return (this.foodRepository.save(food)!=null)?"success":"fail";
	}

	@Override
	public String deleteFoodById(int id) {
		// TODO Auto-generated method stub
		Food optional = this.getFoodById(id);
		if(optional==null) {
			return null;
		}
		else {
			foodRepository.deleteById(id);
			return "success";
		}
		
	}


}