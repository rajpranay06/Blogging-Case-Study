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

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;
import com.example.demo.service.CommentServiceImpl;

@RestController
public class CommentController {

	@Autowired
	CommentServiceImpl comServ;
	
	// Add comment using DTO
	@PostMapping("/comments/dto")
	public ResponseEntity<CommentDto> addCommentDto(@Valid @RequestBody CommentInputDto comment) {
		CommentDto com = comServ.addCommentDto(comment); 
		return new ResponseEntity<>(com, HttpStatus.OK);
	}
	
	// Delete the comment by ID
	@DeleteMapping("/comments/byId/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable("id") int commentId) {
		comServ.deleteComment(commentId);
		return new ResponseEntity<>("Comment with id " + commentId + " is deleted", HttpStatus.OK);
	}
	
	// Get Comment By ID
	public ResponseEntity<CommentOutputDto> getCommentById(@PathVariable("id") int commentId){
		CommentOutputDto commentOutput = comServ.getCommentById(commentId);
		return new ResponseEntity<>(commentOutput, HttpStatus.OK);
	}
	
	// Set upVote
	@PatchMapping("/comments/{commentId}")
	public void upVote(@PathVariable("commentId") int commentId, boolean isupVote) {
		comServ.upVote(commentId, isupVote);
	}
	
	// Get Comments by Post
	@GetMapping("/comments/byPostId/{postId}")
	public ResponseEntity<List<CommentOutputDto>> listAllCommentsOfPost(@PathVariable("postId") int postId){
		List<CommentOutputDto> comments = comServ.listAllCommentsOfPost(postId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	// Get Comments by Blogger
	@GetMapping("/comments/byBloggerId/{bloggerId}")
	public ResponseEntity<List<CommentOutputDto>> listAllCommentsOfBlogger(@PathVariable("bloggerId") int userId){
		List<CommentOutputDto> comments = comServ.listAllCommentsOfBlogger(userId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	// Update the comment
	@PutMapping("/comments/updateComment")
	public ResponseEntity<CommentOutputDto> updateComment(@RequestBody CommentInputDto comment){
		CommentOutputDto updatedComment = comServ.updateComment(comment);
		return new ResponseEntity<>(updatedComment, HttpStatus.ACCEPTED);
	}

	
}