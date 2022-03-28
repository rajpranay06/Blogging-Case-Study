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
import com.example.demo.bean.UserInputDto;
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
	void singOuttest() {
		UserEntity user=new UserEntity(5,"ram@gmail.com","ram@1234","Trader",false);
		Mockito.when(userRepo.findById(5)).thenReturn(Optional.of(user));
		UserEntity userTest=userService.signOut(5);
		assertEquals(5,userTest.getUserId());
		assertEquals("ram@gmail.com",userTest.getEmail());
		assertEquals("ram@1234",userTest.getPassword());
		assertEquals("Trader",userTest.getRole());
		
	}
	
	@Test
	@Disabled
	void singIntest() {
		UserInputDto user=new UserInputDto(10,"samy@gmail.com","samy@1234");
		UserEntity userEntity=new UserEntity();
		userEntity.setUserId(user.getUserId());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
		userEntity.setRole("Admin");
		userEntity.setLoginStatus(true);
		Mockito.when(userRepo.findById(8)).thenReturn(Optional.of(userEntity));
		UserEntity userTest=userService.signIn(user);
		assertEquals(8,userTest.getUserId());
		assertEquals("john@gmail.com",userTest.getEmail());
		assertEquals("john@1234",userTest.getPassword());
		assertEquals("Admin",userTest.getRole());
		assertEquals(true,userTest.isLoginStatus());
	}
	
	

}