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
		comment.setCommentDescription("Test 1");
		comment.setVotes(9);
		comment.setPostId(17);
		
		CommentDto comDto = comServ.addCommentDto(comment);
		assertEquals("Test 1", comDto.getCommentDescription());
		assertEquals(9, comDto.getVotes());
		assertEquals(17, comDto.getPost().getPostId());
	}
	
	@Test
	@Disabled
	void updateCommentTest() {
		CommentInputDto comment = new CommentInputDto();
		comment.setCommentDescription("Updated Test 1");
		comment.setVotes(9);
		comment.setPostId(17);
		
		CommentDto comDto = comServ.addCommentDto(comment);
		assertEquals("Updated Test 1", comDto.getCommentDescription());
		assertEquals(9, comDto.getVotes());
		assertEquals(17, comDto.getPost().getPostId());
	}
	
	@Test
	@Disabled
	void getCommentsByPostIdTest() {
		
		// Calling listAllCommentsByPost Function
		List<CommentOutputDto> comments = comServ.listAllCommentsOfPost(17);
		
		// Comapring the number of comments
		assertEquals(3, comments.size());
	}

}