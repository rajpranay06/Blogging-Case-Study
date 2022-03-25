package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

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

import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.dto.PostInputDto;
import com.example.demo.repository.IPostRepository;

@ExtendWith(SpringExtension.class)
public class PostServiceMockitoTest {

//	// @InjectMocks - Creates instance of a class and injects mocks that are created with @Mock
	@InjectMocks
	PostServiceImpl postServ;
	
	// @MockBean - Creates Mock of a class or interface
	@MockBean
	IPostRepository postRepo;
	
	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void addPostTest() {
		
		// Creating PostInputDto object
		PostInputDto newPost = new PostInputDto(); 
		
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

		// Creating post object
		Post post = new Post();
		
		// Setting the post values
		post.setPostId(newPost.getPostId());
		post.setTitle(newPost.getTitle());
		post.setContent(newPost.getContent());
		post.setCreatedDateTime(newPost.getCreatedDateTime());
		post.setFlair(newPost.getFlair());
		post.setNotSafeForWork(newPost.isNotSafeForWork());
		post.setOriginalContent(newPost.isOriginalContent());
		post.setVotes(newPost.getVotes());
		post.setVoteUp(newPost.isVoteUp());
		post.setSpoiler(newPost.isSpoiler());
		
		// Sending post object when save function is called
		Mockito.when(postRepo.save(post)).thenReturn(post);
		
		// checking if the added post values are equal to the post or not
		assertEquals(100, post.getPostId());
		assertEquals("Lucifer", post.getTitle());
		assertEquals(PostType.VIDEO_IMAGE, post.getContent());
		assertEquals("Deckerstar", post.getFlair());
		assertEquals(10000, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(false, post.isVoteUp());
		
	}
	
	@Test
	void updatePostTest() {

		// Creating PostInputDto object
		PostInputDto updatedPost = new PostInputDto(); 
		
		// Setting the values
		updatedPost.setPostId(59);
		updatedPost.setTitle("Game of Thrones");
		updatedPost.setContent(PostType.LINK);
		updatedPost.setCreatedDateTime(LocalDateTime.now());
		updatedPost.setFlair("GameOfThrones");
		updatedPost.setNotSafeForWork(false);
		updatedPost.setOriginalContent(true);
		updatedPost.setVotes(234578);
		updatedPost.setVoteUp(true);
		updatedPost.setSpoiler(true);
		
		// Creating post object
		Post post = new Post();
		
		// Setting the post values
		post.setPostId(updatedPost.getPostId());
		post.setTitle(updatedPost.getTitle());
		post.setContent(updatedPost.getContent());
		post.setCreatedDateTime(updatedPost.getCreatedDateTime());
		post.setFlair(updatedPost.getFlair());
		post.setNotSafeForWork(updatedPost.isNotSafeForWork());
		post.setOriginalContent(updatedPost.isOriginalContent());
		post.setVotes(updatedPost.getVotes());
		post.setVoteUp(updatedPost.isVoteUp());
		post.setSpoiler(updatedPost.isSpoiler());
		
		// Sending the post object when the following functions are called instead of using database
		Mockito.when(postRepo.findById(59)).thenReturn(Optional.of(post));
		Mockito.when(postRepo.save(post)).thenReturn(post);
		
		// checking if the updated post values are equal to the post or not
		assertEquals(59, post.getPostId());
		assertEquals("Game of Thrones", post.getTitle());
		assertEquals(PostType.LINK, post.getContent());
		assertEquals("GameOfThrones", post.getFlair());
		assertEquals(234578, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(true, post.isVoteUp());
		
	}
	
	@Test
	void deletePostTest() {
		
		// Creating PostInputDto object
		PostInputDto deletedPost = new PostInputDto(); 
		
		// Setting the values
		deletedPost.setPostId(100);
		deletedPost.setTitle("Lucifer");
		deletedPost.setContent(PostType.VIDEO_IMAGE);
		deletedPost.setCreatedDateTime(LocalDateTime.now());
		deletedPost.setFlair("Deckerstar");
		deletedPost.setNotSafeForWork(false);
		deletedPost.setOriginalContent(true);
		deletedPost.setVotes(10000);
		deletedPost.setVoteUp(false);
		deletedPost.setSpoiler(true);

		// Creating post object
		Post post = new Post();
		
		// Setting the post values
		post.setPostId(deletedPost.getPostId());
		post.setTitle(deletedPost.getTitle());
		post.setContent(deletedPost.getContent());
		post.setCreatedDateTime(deletedPost.getCreatedDateTime());
		post.setFlair(deletedPost.getFlair());
		post.setNotSafeForWork(deletedPost.isNotSafeForWork());
		post.setOriginalContent(deletedPost.isOriginalContent());
		post.setVotes(deletedPost.getVotes());
		post.setVoteUp(deletedPost.isVoteUp());
		post.setSpoiler(deletedPost.isSpoiler());
		
		// Sending post object when findById function is called
		Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post));
		
		// Creating a mock to the Post repository class
		IPostRepository postRepository = mock(IPostRepository.class);
		// delete has void return type so do nothing is used
		doNothing().when(postRepository).delete(post);
		
		// checking if the added post values are equal to the post or not
		assertEquals(100, post.getPostId());
		assertEquals("Lucifer", post.getTitle());
		assertEquals(PostType.VIDEO_IMAGE, post.getContent());
		assertEquals("Deckerstar", post.getFlair());
		assertEquals(10000, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(false, post.isVoteUp());		
		
	}
}
