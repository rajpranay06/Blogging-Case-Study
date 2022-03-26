package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.Comment;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;

@SpringBootTest
class CommentServiceTest {

	@Autowired
	ICommentService comServ;

	@Test
	void addCommentTest() {
		Comment comment = new Comment();
		comment.setCommentId(57);
		comment.setCommentDescription("Avg");
		comment.setVotes(9);
		//comment.setVoteUp(false);
		
		CommentOutputDto comDto = comServ.addComment(comment);
		assertEquals("Avg", comDto.getCommentDescription());
		assertEquals(9, comDto.getVotes());
		//assertEquals(57, comDto.get)
	}
	
	@Test
	@Disabled
	void deleteCommentTest() {
		
		Comment comment = comServ.deleteComment(4);
		
		assertEquals("Good", comment.getCommentDescription());
		assertEquals(10, comment.getVotes());
		
	}
	
	@Test
	void addCommentDto() {
		CommentInputDto comDto = new CommentInputDto();
		comDto.setCommentId(1);
		comDto.setCommentDescription("Good");
		comDto.setVotes(3);
		comDto.setVoteUp(false);
		CommentOutputDto comOutDto = comServ.addCommentDto(comDto);
		assertEquals("Good", comOutDto.getCommentDescription());
		assertEquals(3, comOutDto.getVotes());
		assertEquals(false, comOutDto.isVoteUp());
	}

}