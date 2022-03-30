package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
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
	
	public UserInputDto(int userId, String email, String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
	}
	public UserInputDto() {}
	
}
