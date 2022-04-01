package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.bean.UserEntity;
import com.example.demo.dto.UserInputDto;
import com.example.demo.dto.UserOutputDto;
import com.example.demo.repository.IUserRepository;

@ExtendWith(SpringExtension.class)
class UserServiceMockitoTest {

	@InjectMocks
	UserServiceImpl userService;
	
	@MockBean
	IUserRepository userRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void addUserTest() {
		
		UserEntity user = new UserEntity(5,"ram@gmail.com","ram@1234","Trader",false);
				
		// Mockito to save user
		Mockito.when(userRepo.save(user)).thenReturn(user);
		
		// Calling saving function
		UserOutputDto newUser = userService.addUser(user);
		
		assertEquals(5, newUser.getUserId());
		assertEquals("ram@gmail.com", newUser.getEmail());
		assertEquals("Trader", newUser.getRole());
		assertEquals(false, newUser.isLoginStatus());
		
	}
	
	@Test
	void singOuttest() {
		UserEntity user=new UserEntity(5,"ram@gmail.com","ram@1234","Trader",false);
		
		Mockito.when(userRepo.findById(5)).thenReturn(Optional.of(user));
		Mockito.when(userRepo.save(user)).thenReturn(user);
		
		UserOutputDto userTest = userService.signOut(5);
		
		assertEquals(5,userTest.getUserId());
		assertEquals("ram@gmail.com",userTest.getEmail());
		assertEquals("Trader",userTest.getRole());
		
	}
	
	@Test
	void singIntest() {
		
		UserInputDto user=new UserInputDto(8,"john@gmail.com","john@1234", "Admin", true);
		
		UserEntity userEntity=new UserEntity();
		userEntity.setUserId(user.getUserId());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
		userEntity.setRole(user.getRole());
		userEntity.setLoginStatus(user.isLoginStatus());
		
		Mockito.when(userRepo.findById(8)).thenReturn(Optional.of(userEntity));
		Mockito.when(userRepo.save(userEntity)).thenReturn(userEntity);
		
		UserOutputDto userTest=userService.signIn(user);
		
		assertEquals(8,userTest.getUserId());
		assertEquals("john@gmail.com",userTest.getEmail());
		assertEquals("Admin",userTest.getRole());
		assertEquals(true,userTest.isLoginStatus());
	}
	
	@Test
	void getUserByBloggerId() {
		UserEntity user = new UserEntity(8,"john@gmail.com","john@1234", "Admin", true);
		
		Mockito.when(userRepo.getUserByBloggerId(8)).thenReturn(user);
		
		UserOutputDto userTest = userService.getUserByBloggerId(8);
		assertEquals(8,userTest.getUserId());
		assertEquals("john@gmail.com",userTest.getEmail());
		assertEquals("Admin",userTest.getRole());
		assertEquals(true,userTest.isLoginStatus());
	}

}