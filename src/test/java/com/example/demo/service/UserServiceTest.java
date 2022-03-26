package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.UserEntity;

@SpringBootTest
class UserServiceTest {

	@Autowired
	IUserService userService;
	
	@Test
	void getAllUserstest() {
		List<UserEntity> users = userService.getAllUsers();
		int noOfUsers = users.size();
		assertEquals(5,noOfUsers);
	}
	
	@Test
	void addNewUsertest() {
		UserEntity user=new UserEntity();
		user.setEmail("kevin@gmail.com");
		user.setPassword("kevin@1234");
		user.setRole("Admin");
		
		assertEquals("kevin@gmail.com",user.getEmail());
		assertEquals("kevin@1234",user.getPassword());
		assertEquals("Admin",user.getRole());
	}
	
	@Test
	void signOuttest() {
		UserEntity user=new UserEntity();
		user.setEmail("bob@gmail.com");
		user.setPassword("bob@1234");
		
		assertEquals("bob@gmail.com",user.getEmail());
		assertEquals("bob@1234",user.getPassword());
	}

}