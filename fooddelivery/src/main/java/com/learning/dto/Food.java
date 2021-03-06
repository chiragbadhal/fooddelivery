package com.learning.dto;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Food {
	 

	@Size(max=20)
	@NotNull
	private String foodName;
	
	@NotNull
	private String foodCost;
	
	
    private FoodType foodType;
	
	@Size(max=50)
	private String description;
	
	@NotNull
	private String foodPic;
	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long foodId;
	

}
