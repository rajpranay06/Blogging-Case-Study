package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

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
	
	// Get all awards
		@GetMapping("/awards")
		List<Award> viewAllAwards() {
			return awardServ.viewAllAwards();
		}
	
	// Add Awards
	@PostMapping("/awards")
	ResponseEntity<Award> addAward(@Valid @RequestBody Award award){
		Award aw = awardServ.addAward(award);
		return new ResponseEntity<>(aw, HttpStatus.OK);
	}
	
	// Delete award by id
	@DeleteMapping("/awards/{id}")
	ResponseEntity<String> deleteAwardById(@PathVariable("id") int id){
		awardServ.deleteAwardById(id);
		return new ResponseEntity<>("Award with id " + id + " is deleted", HttpStatus.OK);
	}
	
	// Get Awards by post id
	@GetMapping("/awards/post/{id}")
	ResponseEntity<List<Award>> getAwardsByPostId(@PathVariable("id") int id){
		List<Award> aw = awardServ.getAwardByPostId(id);
		return new ResponseEntity<>(aw, HttpStatus.OK);
	}
	
	// Get Awards by Blog id
	@GetMapping("/awards/byBlogger/{bloggerId}")
	ResponseEntity<List<Award>> getAwardsByBlogId(@PathVariable("bloggerId") int id){
		List<Award> aw = awardServ.getAwardsByBlogId(id);
		return new ResponseEntity<>(aw, HttpStatus.OK);
	}
}
