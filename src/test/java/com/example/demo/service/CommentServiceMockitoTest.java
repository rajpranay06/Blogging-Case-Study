package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

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
import com.example.demo.repository.ICommentRepository;

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
		Comment comment = new Comment(3, "Good", 5, true);
		Mockito.when(comRepo.save(comment)).thenReturn(comment);
		assertEquals(3, comment.getCommentId());
		assertEquals("Good", comment.getCommentDescription());
		assertEquals(5, comment.getVotes());
		assertEquals(true, comment.isVoteUp());
	}
	
	@Test
	void deleteCommentTest() {
		Comment comment = new Comment(3, "Good", 5, true);
		Mockito.when(comRepo.findById(comment.getCommentId())).thenReturn(Optional.of(comment));
		
		if(comment!=null) {
			assertEquals(3, comment.getCommentId());
			assertEquals("Good", comment.getCommentDescription());
			assertEquals(5, comment.getVotes());
			assertEquals(true, comment.isVoteUp());
			comService.deleteComment(comment);
		}
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
