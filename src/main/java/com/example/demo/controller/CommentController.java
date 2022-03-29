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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Comment;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;
import com.example.demo.service.CommentServiceImpl;

@RestController
public class CommentController {

	@Autowired
	CommentServiceImpl comServ;
	
	// Add new Comment
	@PostMapping("/comments")
	public ResponseEntity<CommentOutputDto> addComment(@Valid @RequestBody Comment comment) {
		CommentOutputDto com = comServ.addComment(comment); 
		return new ResponseEntity<>(com, HttpStatus.OK);
	}
	
	// Add comment using DTO
	@PostMapping("/comments/dto")
	public ResponseEntity<CommentOutputDto> addCommentDto(@Valid @RequestBody CommentInputDto comment) {
		CommentOutputDto com = comServ.addCommentDto(comment); 
		return new ResponseEntity<>(com, HttpStatus.OK);
	}
	
	// Delete the comment by ID
	@DeleteMapping("/comments/byId/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable("id") int commentId) {
		comServ.deleteComment(commentId);
		return new ResponseEntity<>("Comment with id " + commentId + " is deleted", HttpStatus.OK);
	}
	
	// Get Comment By ID
	@PatchMapping("/comments/{commentId}")
	public void upVote(@PathVariable("commentId") int commentId, boolean isupVote) {
		comServ.upVote(commentId, isupVote);
	}
	
	// Get Comments by Post
	@GetMapping("/comments/byPostId/{postId}")
	public ResponseEntity<List<Comment>> listAllCommentsOfPost(@PathVariable("postId") int postId){
		List<Comment> comments = comServ.listAllCommentsOfPost(postId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	// Get Comments by Blogger
	@GetMapping("/comments/byBloggerId/{userId}")
	public ResponseEntity<List<Comment>> listAllCommentsOfBlogger(@PathVariable("userId") int userId){
		List<Comment> comments = comServ.listAllCommentsOfBlogger(userId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}

	
}