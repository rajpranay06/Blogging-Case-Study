package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Award;
import com.example.demo.service.IAwardService;

@RestController
public class AwardController {

	@Autowired
	IAwardService awardServ;
	
	@PostMapping("/awards")
	ResponseEntity<Award> addAward(@RequestBody Award award){
		Award aw = awardServ.addAward(award);
		return new ResponseEntity<>(aw, HttpStatus.OK);
	}
	
	@DeleteMapping("/awards/{id}")
	ResponseEntity<Award> deleteAwardById(@PathVariable("id") int id){
		Award aw = awardServ.deleteAwardById(id);
		return new ResponseEntity<>(aw, HttpStatus.OK);
	}
	
	@GetMapping("/awards/post/{id}")
	ResponseEntity<List<Award>> getAwardsByPostId(@PathVariable("id") int id){
		List<Award> aw = awardServ.getAwardByPostId(id);
		return new ResponseEntity<>(aw, HttpStatus.OK);
	}
}
