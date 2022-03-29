package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
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
import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.repository.IPostRepository;

@ExtendWith(SpringExtension.class)
class CommentServiceMockitoTest {

	@InjectMocks
	CommentServiceImpl comService;
	
	@MockBean
	ICommentRepository comRepo;
	
	@MockBean
	IPostRepository postRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	void addCommentTest() {
		Comment comment = new Comment();
		comment.setCommentId(10);
		comment.setCommentDescription("test1");
		comment.setVotes(10);
		comment.setVoteUp(true);
		
		Post newPost = new Post();
		// Setting the values
		newPost.setPostId(100);
		newPost.setTitle("Lucifer");
		newPost.setContent(PostType.VIDEO_IMAGE);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("Deckerstar");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(10000);
		newPost.setVoteUp(false);
		newPost.setSpoiler(true);
		
		comment.setPost(newPost);
		
		Mockito.when(comRepo.save(comment)).thenReturn(comment);
		
		CommentDto newComment = comService.addComment(comment);
		
		assertEquals(10, newComment.getCommentId());
		assertEquals("test1", newComment.getCommentDescription());
		assertEquals(10, newComment.getVotes());
		assertEquals(true, newComment.isVoteUp());
		assertEquals(100, newComment.getPost().getPostId());
	}
	
	
	@Test
	void updateCommentTest() {
		
		CommentInputDto com = new CommentInputDto();
		com.setCommentId(10);
		com.setCommentDescription("test1");
		com.setPostId(100);
		com.setVotes(10);
		com.setVoteUp(true);
		
		Comment comment = new Comment();
		comment.setCommentId(com.getCommentId());
		comment.setCommentDescription(com.getCommentDescription());
		comment.setVotes(com.getVotes());
		comment.setVoteUp(com.isVoteUp());
		
		Post newPost = new Post();
		// Setting the values
		newPost.setPostId(100);
		newPost.setTitle("Lucifer");
		newPost.setContent(PostType.VIDEO_IMAGE);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("Deckerstar");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(10000);
		newPost.setVoteUp(false);
		newPost.setSpoiler(true);
		
		comment.setPost(newPost);
		
		Mockito.when(comRepo.findById(10)).thenReturn(Optional.of(comment));
		
		Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(newPost));
		
		Mockito.when(comRepo.save(comment)).thenReturn(comment);
		
		CommentOutputDto newComment = comService.updateComment(com);
		
		assertEquals(10, newComment.getCommentId());
		assertEquals("test1", newComment.getCommentDescription());
		assertEquals(10, newComment.getVotes());
		assertEquals(true, newComment.isVoteUp());
	}
	
}
