package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.UserEntity;

@SpringBootTest
class UserServiceTest {

	@Autowired
	IUserService userService;
	
	@Test
	@Disabled
	void getAllUserstest() {
		
		// Getting all the users
		List<UserEntity> users = userService.getAllUsers();
		
		// Comparing the number of users
		assertEquals(8,users.size());
	}
	
	@Test
	@Disabled
	void addNewUsertest() {
		
		// Created User Entity
		UserEntity user=new UserEntity();
		user.setEmail("kevin@gmail.com");
		user.setPassword("kevin@1234");
		user.setRole("Admin");
		
		// Added User Entity
		UserEntity addedUser = userService.addNewUser(user);
		
		// Comparing values
		assertEquals("kevin@gmail.com",addedUser.getEmail());
		assertEquals("kevin@1234",addedUser.getPassword());
		assertEquals("Admin",addedUser.getRole());
	}
	
	@Test
	@Disabled
	void signOuttest() {
		
		UserEntity user=new UserEntity();
		user.setUserId(15);
		user.setEmail("test4@gmail.com");
		user.setPassword("test4@123");
		
		UserEntity signedOutUser = userService.signOut(user.getUserId());
		
		assertEquals(15, signedOutUser.getUserId());
		assertEquals("test4@gmail.com",signedOutUser.getEmail());
		assertEquals("test4@123",signedOutUser.getPassword());
	}

}