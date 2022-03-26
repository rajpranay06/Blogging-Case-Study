package com.example.demo.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
public class UserInputDto {

	@Id
	@GeneratedValue
	private int UserId;
	private String email;
	private String password;
	//private String role;
	public UserInputDto(int userId, String email, String password) {
		super();
		UserId = userId;
		this.email = email;
		this.password = password;
	}
	
	
	
}

