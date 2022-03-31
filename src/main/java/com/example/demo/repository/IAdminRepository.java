package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
