package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Admin {

	@Id
	private int userId;
	@Size(min=3,message="Minimum 3 characters required")
	private String adminName;
	@Pattern(regexp="^[6-9]{1}[0-9]{9}$", message = "Enter a valid mobile number")
	private String adminContact;
	
}
