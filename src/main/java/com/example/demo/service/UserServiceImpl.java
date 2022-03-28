package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Admin;
import com.example.demo.bean.UserEntity;
import com.example.demo.bean.UserInputDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.IAdminRepository;
import com.example.demo.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	IAdminRepository adminRepo;
	
	//@Autowired
	//IUserService userServ;

	
	@Override
	public UserEntity addNewUser(UserEntity user) {
		/*UserInputDto userInputDto=new UserInputDto();
		userInputDto.setUserId(user.getUserId());
		userInputDto.setEmail(user.getEmail());
		userInputDto.setPassword(user.getPassword());
		userInputDto.setRole(user.getRole());
		return userInputDto;*/
		return userRepo.save(user);
	}

	@Override
	public UserEntity signIn(UserInputDto user) {
	/*	UserEntity userEntity=new UserEntity();
		userEntity.setUserId(user.getUserId());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
		userEntity.setRole(user.getRole());*/
		
		UserEntity dbuser= userRepo.getById(user.getUserId());
	/*	user.setUserId(dbuser.getUserId());
		user.setEmail(dbuser.getEmail());
		user.setPassword(dbuser.getPassword());
		user.setRole(dbuser.getRole());*/
		
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
	/*	UserEntity user= userRepo.getById(id);
		user.setLoginStatus(false);
		return userRepo.save(user);*/
		
		UserEntity user=new UserEntity();
		Optional<UserEntity> opt=userRepo.findById(id);
		if(!opt.isPresent()) {
			throw new UserNotFoundException("User not found with id: "+ id);
		}
		user.setLoginStatus(false);
		//userRepo.deleteById(id);
		return opt.get();
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepo.findAll();
	}


	@Override
	public Admin addAdmin(Admin admin) {
	/*	UserEntity user=new UserEntity();
		user.setUserId(admin.getUserId());
		Admin ad=new Admin();
		ad.setAdminName(admin.getAdminName());
		ad.setAdminContact(admin.getAdminContact());
		user.setAdmin(ad);*/
		return adminRepo.save(admin);
	}

	

}