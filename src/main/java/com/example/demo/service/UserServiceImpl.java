package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Admin;
import com.example.demo.bean.UserEntity;
import com.example.demo.dto.UserInputDto;
import com.example.demo.dto.UserOutputDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.IAdminRepository;
import com.example.demo.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	IAdminRepository adminRepo;
	
	@Override
	public UserOutputDto addNewUser(UserInputDto user) {
		
		UserEntity addUser = new UserEntity();
		addUser.setEmail(user.getEmail());
		addUser.setPassword(user.getPassword());
		addUser.setLoginStatus(user.isLoginStatus());
		addUser.setRole(user.getRole());
	
		UserEntity newUser = userRepo.save(addUser);
		
		// Creating UserOutputDto
		UserOutputDto userOutput = new UserOutputDto();
		userOutput.setEmail(newUser.getEmail());
		userOutput.setUserId(newUser.getUserId());
		userOutput.setLoginStatus(newUser.isLoginStatus());
		userOutput.setRole(newUser.getRole());
		
		return userOutput;
	}

	@Override
	public UserOutputDto signIn(UserInputDto user) {
		
		Optional<UserEntity> opt = userRepo.findById(user.getUserId());
		
		if(!opt.isPresent()) {
			throw new UserNotFoundException("No user is found with id: " + user.getUserId());
		}
		
		UserEntity dbuser = opt.get();
		
		//compare login and dblogin details
		if(user.getEmail().equals(dbuser.getEmail()) && user.getPassword().equals(dbuser.getPassword())) {
			dbuser.setLoginStatus(true);
			UserEntity updatedUser = userRepo.save(dbuser);
			
			// Creating UserOutputDto
			UserOutputDto userOutput = new UserOutputDto();
			userOutput.setEmail(updatedUser.getEmail());
			userOutput.setUserId(updatedUser.getUserId());
			userOutput.setLoginStatus(updatedUser.isLoginStatus());
			userOutput.setRole(updatedUser.getRole());
			
			return userOutput;
		}
		throw new UserNotFoundException("Invalid Credentials");
	}

	@Override
	public UserOutputDto signOut(int id) {

		Optional<UserEntity> opt = userRepo.findById(id);
		if(!opt.isPresent()) {
			throw new UserNotFoundException("User not found with id: "+ id);
		}
		
		// Getting the User
		UserEntity user = opt.get();
		user.setLoginStatus(false);
		
		UserEntity signOutUser = userRepo.save(user);
	
		// Creating UserOutputDto
		UserOutputDto userOutput = new UserOutputDto();
		userOutput.setEmail(signOutUser.getEmail());
		userOutput.setUserId(signOutUser.getUserId());
		userOutput.setLoginStatus(signOutUser.isLoginStatus());
		userOutput.setRole(signOutUser.getRole());
		
		return userOutput;
	}

	@Override
	public List<UserOutputDto> getAllUsers() {
		// Creating list of userOutputDto
		List<UserOutputDto> users = new ArrayList<>();
		
		for(UserEntity user : userRepo.findAll()) {
			
			// Creating UserOutputDto
			UserOutputDto userOutput = new UserOutputDto();
			userOutput.setEmail(user.getEmail());
			userOutput.setUserId(user.getUserId());
			userOutput.setLoginStatus(user.isLoginStatus());
			userOutput.setRole(user.getRole());
			
			users.add(userOutput);
		}
		
		return users;
	}

	@Override
	public Admin addAdmin(int userId, Admin admin) {
		
		if(userId != admin.getUserId()) {
			throw new UserNotFoundException("The user id should be same for admin and user");
		}
		
		// Checking if user role is matching with Admin or not
		if(!userRepo.getById(userId).getRole().equals("Admin")) {
			throw new UserNotFoundException("This user is not an admin"); 
		}
		// Saving admin
		Admin ad = adminRepo.save(admin);
		
		UserEntity user = new UserEntity();
		// Setting admin to user
		user.setAdmin(ad);
	
		return ad;
	}
	
	@Override
	public UserOutputDto getUserByBloggerId(int bloggerId) {
		UserEntity user = userRepo.getUserByBloggerId(bloggerId);
		if(user == null) {
			throw new UserNotFoundException("No blogger found with blogger id:" + bloggerId);
		}
		// Creating UserOutputDto
		UserOutputDto userOutput = new UserOutputDto();
		userOutput.setEmail(user.getEmail());
		userOutput.setUserId(user.getUserId());
		userOutput.setLoginStatus(user.isLoginStatus());
		userOutput.setRole(user.getRole());
		
		return userOutput;
	}

}