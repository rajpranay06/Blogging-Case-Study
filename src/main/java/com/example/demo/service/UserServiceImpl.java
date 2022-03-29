package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Admin;
import com.example.demo.bean.UserEntity;
import com.example.demo.dto.UserInputDto;
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
	public UserEntity addNewUser(UserEntity user) {
	
		return userRepo.save(user);
	}

	@Override
	public UserEntity signIn(UserInputDto user) {
		
		UserEntity dbuser= userRepo.getById(user.getUserId());
		
		//compare login and dblogin details
		if(user.getEmail().equals(dbuser.getEmail()) && user.getPassword().equals(dbuser.getPassword())) {
			dbuser.setLoginStatus(true);
			userRepo.save(dbuser);
			return dbuser;
		}
		return null;
	}

	@Override
	public UserEntity signOut(int id) {

		
		UserEntity user=new UserEntity();
		Optional<UserEntity> opt=userRepo.findById(id);
		if(!opt.isPresent()) {
			throw new UserNotFoundException("User not found with id: "+ id);
		}
		user.setLoginStatus(false);
	
		return opt.get();
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepo.findAll();
	}


	@Override
	public Admin addAdmin(Admin admin) {
	
		return adminRepo.save(admin);
	}

	

}