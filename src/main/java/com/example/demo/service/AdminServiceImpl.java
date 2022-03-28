package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Admin;
import com.example.demo.repository.IAdminRepository;

@Service
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	IAdminRepository adminRepo;
	

	@Override
	public Admin addAdmin(Admin admin) {
		return adminRepo.save(admin);
	}

}

