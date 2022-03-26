package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Admin {

	@Id
	private int userId;
	@Size(min=3,message="Minimum 3 characters required")
	private String adminName;
	@Size(min=10,max=10,message="Minimum 10 number required")
	private String adminContact;
	
}
