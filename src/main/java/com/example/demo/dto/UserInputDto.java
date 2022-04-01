package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDto {

	@Id
	@GeneratedValue
	private int userId;
	@Email(message = "enter a valid email")
	private String email;
	@Size(min=8,max=15, message="Min 8 characters required")
	private String password;
	private String role;
	private boolean loginStatus;
	
}
