package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.dto.UserInputDto;
import com.example.demo.dto.UserOutputDto;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IUserService;

@RestController
public class UserController {

	@Autowired
	IUserService userServ;
	
	@Autowired
	IUserRepository userRepo;
	
	// Get all users
	@GetMapping("/users")
	List<UserOutputDto> getAllTrainees(){
		return userServ.getAllUsers();
	}
	
	// Adding new user
	@PostMapping("/users")
	ResponseEntity<UserOutputDto> addNewUser(@Valid @RequestBody UserInputDto user) {
		UserOutputDto newUser=userServ.addNewUser(user);
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
	
	// Sign in
	@PostMapping("/usersLogin")
	ResponseEntity<UserOutputDto> signIn(@Valid @RequestBody UserInputDto user) {
		UserOutputDto log=userServ.signIn(user);
		return new ResponseEntity<>(log,HttpStatus.OK);
	}
	
	// Logout
	@GetMapping("/users/{id}")
	ResponseEntity<UserOutputDto> signOut(@PathVariable int id) {
		UserOutputDto logout=userServ.signOut(id);
		return new ResponseEntity<>(logout,HttpStatus.OK);
	}
	
	// Add admin
	@PostMapping("/users/byRole/{userId}")
	ResponseEntity<Admin> addAdmin(@PathVariable int userId, @Valid @RequestBody Admin admin){
		Admin addedAdmin = userServ.addAdmin(userId, admin);
		return new ResponseEntity<>(addedAdmin,HttpStatus.CREATED);
	}
	
	// Get User by Blogger Id
	@GetMapping("/users/byBlogger/{bloggerId}")
	ResponseEntity<UserOutputDto> getUserByBloggerId(@PathVariable("bloggerId") int bloggerId){
		UserOutputDto userEntity = userServ.getUserByBloggerId(bloggerId);
		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}
	
	
}
