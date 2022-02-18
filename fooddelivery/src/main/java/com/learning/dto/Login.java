package com.learning.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.learning.dto.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Login 
{
	@Id
	@Email // giving the email constraint 
	@NotNull
	private String email;
	
	@Size(min=8)
	@NotNull
	private String password;
	
//	@OneToOne
//    @JsonIgnoreProperties("\"hibernateLazyInitializer\",\"handler\"")
//
//    @JsonProperty(access=Access.WRITE_ONLY)
//    @JoinColumn(name = "Id")
//	private User register;

	

}
