package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.service.IPostService;

@RestController
public class PostController {
	
	@Autowired           // Creates object to the class
	IPostService postServ;
	
	// Adding Post
	@PostMapping("/posts")
	ResponseEntity<PostOutputDto> addPost(@Valid @RequestBody PostInputDto post){
		PostOutputDto newPost = postServ.addPost(post);
		return new ResponseEntity<>(newPost, HttpStatus.ACCEPTED);
	}
	
	// Updating Post
	@PutMapping("/posts")
	ResponseEntity<PostOutputDto> updatePost(@Valid @RequestBody PostInputDto post){
		PostOutputDto updatedPost = postServ.updatePost(post);
		return new ResponseEntity<>(updatedPost, HttpStatus.ACCEPTED);
	}
	
	// Deleting Post
	@DeleteMapping("/posts/{id}")
	ResponseEntity<PostOutputDto> deletePost(@PathVariable("id") int id){
		PostOutputDto deletedPost = postServ.deletePost(id);
		return new ResponseEntity<>(deletedPost, HttpStatus.OK);
	}
	
	// Get Posts by Searching
	@GetMapping("/posts/bySearchString/{searchString}")
	ResponseEntity<List<PostOutputDto>> getPostsBySearchString(@PathVariable("searchString") String searchStr){
		List<PostOutputDto> posts = postServ.getPostBySearchString(searchStr);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	// Upvoting the post
	@PatchMapping("/posts/upVote/{postId}")
	void upVote(@PathVariable("postId") int postId, boolean isUpVote) {
		postServ.upVote(postId, isUpVote);
	}
}
