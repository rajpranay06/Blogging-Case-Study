package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Admin;
import com.example.demo.bean.UserEntity;
import com.example.demo.dto.UserInputDto;
import com.example.demo.dto.UserOutputDto;

public interface IUserService {

	public UserOutputDto signOut(int id);
	public List<UserOutputDto> getAllUsers();
	public UserOutputDto signIn(UserInputDto user);
	public Admin addAdmin(int userId, Admin admin);
	public UserOutputDto addNewUser(UserInputDto user);
	public UserOutputDto getUserByBloggerId(int bloggerId);
	public UserOutputDto addUser(UserEntity user);
	
}