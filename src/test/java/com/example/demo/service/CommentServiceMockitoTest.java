package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.bean.Comment;
import com.example.demo.dto.CommentOutputDto;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.repository.IPostRepository;

@ExtendWith(SpringExtension.class)
class CommentServiceMockitoTest {

	@InjectMocks
	CommentServiceImpl comService;
	
	@MockBean
	ICommentRepository comRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	void addCommentTest() {
		Comment comment1 = new Comment(3, "Good", 5, true);
		Mockito.when(comRepo.save(comment1)).thenReturn(comment1);
		
		CommentOutputDto comment = comService.addComment(comment1);
		
		assertEquals(3, comment.getCommentId());
		assertEquals("Good", comment.getCommentDescription());
		assertEquals(5, comment.getVotes());
		assertEquals(true, comment.isVoteUp());
	}
	
	@Test
	void deleteCommentTest() {
		Comment comment = new Comment(3, "Good", 5, true);
		
		// Sending post object when findById function is called
		Mockito.when(comRepo.findById(3)).thenReturn(Optional.of(comment));
				
		// delete has void return type so do nothing is used
		doNothing().when(comRepo).delete(comment);
		
		comService.deleteComment(3);
		
		assertEquals(3, comment.getCommentId());
		assertEquals("Good", comment.getCommentDescription());
		assertEquals(5, comment.getVotes());
		assertEquals(true, comment.isVoteUp());
	}
	
	@Test
	void addCommentDto() {
		Comment comment = new Comment(3, "Good", 5, true);
		Mockito.when(comRepo.save(comment)).thenReturn(comment);
		assertEquals(3, comment.getCommentId());
		assertEquals("Good", comment.getCommentDescription());
		assertEquals(5, comment.getVotes());
		assertEquals(true, comment.isVoteUp());
	}

}