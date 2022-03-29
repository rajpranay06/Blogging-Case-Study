package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Admin;
import com.example.demo.bean.UserEntity;
import com.example.demo.bean.UserInputDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IAdminService;
import com.example.demo.service.IUserService;

@RestController
public class UserController {

	@Autowired
	IUserService userServ;
	
	@Autowired
	IAdminService adminServ;
	
	@Autowired
	IUserRepository userRepo;
	
	// Get all users
	@GetMapping("/users")
	List<UserEntity> getAllTrainees(){
		return userServ.getAllUsers();
	}
	
	// Adding new user
	@PostMapping("/users")
	ResponseEntity<UserEntity> addNewUser(@Valid @RequestBody UserEntity user) {
		UserEntity newUser=userServ.addNewUser(user);
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
	
	// Sign in
	@PostMapping("/usersLogin")
	ResponseEntity<UserEntity> signIn(@RequestBody UserInputDto user) {
		UserEntity userDto=new UserEntity();
		userDto.setUserId(user.getUserId());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setLoginStatus(true);
		//UserEntity log=userServ.signIn(userDto);
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
	// Logout
	@GetMapping("/users/{id}")
	ResponseEntity<UserEntity> signOut(@PathVariable int id) {
		UserEntity logout=userServ.signOut(id);
		return new ResponseEntity<>(logout,HttpStatus.OK);
	}
	
	// Add admin
	@PostMapping("/users/byRole/{id}")
	ResponseEntity<Admin> addAdmin(@PathVariable int id, @Valid @RequestBody Admin admin){
		if(!userRepo.getById(id).getRole().equals("Admin")) {
			throw new UserNotFoundException("This user is not an admin"); 
		}
		else {
			Admin ad=adminServ.addAdmin(admin);
			UserEntity user=new UserEntity();
			user.setAdmin(ad);

			return new ResponseEntity<>(ad,HttpStatus.CREATED);
		}
		
	}
	
	
}
