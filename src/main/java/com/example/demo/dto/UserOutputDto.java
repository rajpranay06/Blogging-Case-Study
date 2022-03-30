package com.example.demo.dto;

import lombok.Data;

@Data
public class UserOutputDto {
	
	private int userId;
	private String email;
	private boolean loginStatus;
	private String role;
}
