package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.UserEntity;
import com.example.demo.dto.UserInputDto;
import com.example.demo.dto.UserOutputDto;

@SpringBootTest
class UserServiceTest {

	@Autowired
	IUserService userService;
	
	@Test
	void getAllUserstest() {
		
		// Getting all the users
		List<UserOutputDto> users = userService.getAllUsers();
		
		// Comparing the number of users
		assertEquals(7,users.size());
	}
	@Test
	void signOuttest() {
		
		UserEntity user=new UserEntity();
		user.setUserId(4);
		user.setEmail("test4@gmail.com");
		user.setPassword("test4@123");
		
		UserOutputDto signedOutUser = userService.signOut(user.getUserId());
		
		assertEquals(4, signedOutUser.getUserId());
		assertEquals("test4@gmail.com",signedOutUser.getEmail());
	}

	@Test
	void getUserByBloggerId() {
		
		UserOutputDto userEntity = userService.getUserByBloggerId(15);
		
		assertEquals(1, userEntity.getUserId());
		assertEquals( "test1@gmail.com", userEntity.getEmail());
	}
}