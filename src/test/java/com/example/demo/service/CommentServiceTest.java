package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;

@SpringBootTest
class CommentServiceTest {

	@Autowired
	ICommentService comServ;

	@Test
	//@Disabled
	void addCommentTest() {
		CommentInputDto comment = new CommentInputDto();
		comment.setCommentDescription("Test 1");
		comment.setVotes(9);
		comment.setPostId(43);
		comment.setBloggerId(40);
		
		CommentDto comDto = comServ.addCommentDto(comment);
		assertEquals("Test 1", comDto.getCommentDescription());
		assertEquals(9, comDto.getVotes());
		assertEquals(43, comDto.getPost().getPostId());
		assertEquals(40, comDto.getBlogger().getUserId());
	}
	
	@Test
	//@Disabled
	void updateCommentTest() {
		CommentInputDto comment = new CommentInputDto();
		comment.setCommentId(41);
		comment.setCommentDescription("Updated Test 1");
		comment.setVotes(9);
		comment.setPostId(43);
		comment.setBloggerId(40);
		
		CommentOutputDto comDto = comServ.updateComment(comment);
		assertEquals(41, comDto.getCommentId());
		assertEquals("Updated Test 1", comDto.getCommentDescription());
		assertEquals(9, comDto.getVotes());
	}
	
	
	@Test
	//@Disabled
	void getCommentByIdTest() {
		
		CommentOutputDto comment = comServ.getCommentById(49);
		
		assertEquals("First Comment",comment.getCommentDescription());
		assertEquals(true,comment.isVoteUp());
		assertEquals(10,comment.getVotes());
	}
	
	@Test
	//@Disabled
	void getCommentsByPostIdTest() {
		
		// Calling listAllCommentsByPost Function
		List<CommentOutputDto> comments = comServ.listAllCommentsOfPost(47);
		
		// Comapring the number of comments
		assertEquals(1, comments.size());
	}
	
	@Test
	//@Disabled
	void getCommentsByBloggerIdTest() {
		
		// Calling listAllCommentsByPost Function
		List<CommentOutputDto> comments = comServ.listAllCommentsOfBlogger(48);
		
		// Comapring the number of comments
		assertEquals(1, comments.size());
	}

}