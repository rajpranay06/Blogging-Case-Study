package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Admin;
//import com.example.demo.bean.Admin;
import com.example.demo.bean.UserEntity;
import com.example.demo.bean.UserInputDto;

public interface IUserService {

	//UserEntity addNewUser(UserEntity user);
	UserEntity signOut(int id);
	List<UserEntity> getAllUsers();
	UserEntity signIn(UserInputDto user);
	Admin addAdmin(Admin admin);
	UserEntity addNewUser(UserEntity user);
//	int getById(int id);
	
}