package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.UserEntity;
import com.example.demo.dto.PostOutputDto;

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
	void addNewUserTest() {
		
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
	void signOutTest() {
		
		UserEntity user=new UserEntity();
		user.setUserId(123);
		user.setEmail("ram@gmail.com");
		user.setPassword("ram@1234");
		
		UserEntity signedOutUser = userService.signOut(user.getUserId());
		
		assertEquals(123, signedOutUser.getUserId());
		assertEquals("ram@gmail.com",signedOutUser.getEmail());
		assertEquals("ram@1234",signedOutUser.getPassword());
	}
	
	@Test
	void getUserByBloggerId() {
		UserEntity userEntity = userService.getUserByBloggerId(141);
		assertEquals(135, userEntity.getUserId());
		assertEquals( "ram@gmail.com", userEntity.getEmail());
	}

}