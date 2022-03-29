package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Admin;
import com.example.demo.bean.UserEntity;
import com.example.demo.dto.UserInputDto;

public interface IUserService {

	public UserEntity signOut(int id);
	public List<UserEntity> getAllUsers();
	public UserEntity signIn(UserInputDto user);
	public Admin addAdmin(Admin admin);
	public UserEntity addNewUser(UserEntity user);
	
}