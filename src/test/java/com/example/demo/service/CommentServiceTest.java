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
	@Disabled
	void addCommentTest() {
		CommentInputDto comment = new CommentInputDto();
		comment.setCommentDescription("Comment Test 2");
		comment.setVotes(90);
		comment.setPostId(25);
		comment.setBloggerId(24);
		
		CommentDto comDto = comServ.addCommentDto(comment);
		assertEquals("Comment Test 2", comDto.getCommentDescription());
		assertEquals(90, comDto.getVotes());
		assertEquals(25, comDto.getPost().getPostId());
		assertEquals(24, comDto.getBlogger().getBloggerId());
	}
	
	@Test
	void updateCommentTest() {
		CommentInputDto comment = new CommentInputDto();
		comment.setCommentId(17);
		comment.setCommentDescription("Updated Test 1");
		comment.setVotes(90);
		comment.setPostId(16);
		comment.setBloggerId(24);
		
		CommentOutputDto comDto = comServ.updateComment(comment);
		assertEquals(17, comDto.getCommentId());
		assertEquals("Updated Test 1", comDto.getCommentDescription());
		assertEquals(90, comDto.getVotes());
	}
	
	
	@Test
	void getCommentByIdTest() {
		
		CommentOutputDto comment = comServ.getCommentById(18);
		
		assertEquals("Updated Comment 1",comment.getCommentDescription());
		assertEquals(true,comment.isVoteUp());
		assertEquals(19,comment.getVotes());
	}
	
	@Test
	void getCommentsByPostIdTest() {
		
		// Calling listAllCommentsByPost Function
		List<CommentOutputDto> comments = comServ.listAllCommentsOfPost(16);
		
		// Comapring the number of comments
		assertEquals(3, comments.size());
	}
	
	@Test
	void getCommentsByBloggerIdTest() {
		
		// Calling listAllCommentsByPost Function
		List<CommentOutputDto> comments = comServ.listAllCommentsOfBlogger(15);
		
		// Comapring the number of comments
		assertEquals(1, comments.size());
	}

}