package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.bean.Comment;
import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.repository.IPostRepository;

@ExtendWith(SpringExtension.class)
public class PostServiceMockitoTest {

//	// @InjectMocks - Creates instance of a class and injects mocks that are created with @Mock
	@InjectMocks
	PostServiceImpl postServ;
	
	// @MockBean - Creates Mock of a class or interface
	@MockBean
	IPostRepository postRepo;
	
	@MockBean
	ICommentRepository comRepo;
	
	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void addPost() {
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
		
		Comment comment1 = new Comment();
		comment1.setCommentId(26);
		comment1.setCommentDescription("Awesome");
		comment1.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(26)).thenReturn(Optional.of(comment1));
		
		Comment comment2 = new Comment();
		comment2.setCommentId(27);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(27)).thenReturn(Optional.of(comment2));
		
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);
		
		newPost.setComments(comments);
		
		// Sending post object when save function is called
		Mockito.when(postRepo.save(newPost)).thenReturn(newPost);
		
		Post addedPost = postServ.addPostWithoutDto(newPost);	

		// checking if the added post values are equal to the post or not
		assertEquals(100, addedPost.getPostId());
		assertEquals("Lucifer", addedPost.getTitle());
		assertEquals(PostType.VIDEO_IMAGE, addedPost.getContent());
		assertEquals("Deckerstar", addedPost.getFlair());
		assertEquals(10000, addedPost.getVotes());
		assertEquals(false, addedPost.isNotSafeForWork());
		assertEquals(true, addedPost.isOriginalContent());
		assertEquals(true, addedPost.isSpoiler());
		assertEquals(false, addedPost.isVoteUp());
		assertEquals(2, addedPost.getComments().size());
	}
	
	@Disabled
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
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(16);
		commentIds.add(17);
				
		newPost.setCommentIds(commentIds);

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
		
		Comment comment1 = new Comment();
		comment1.setCommentId(26);
		comment1.setCommentDescription("Awesome");
		comment1.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(16)).thenReturn(Optional.of(comment1));
		
		Comment comment2 = new Comment();
		comment2.setCommentId(27);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(17)).thenReturn(Optional.of(comment2));
		
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);
		
		post.setComments(comments);
		System.out.println(post);

		// Sending post object when save function is called
		Mockito.when(postRepo.save(post)).thenReturn(post);
		
		PostOutputDto addedPost = postServ.addPost(newPost);
		
		// checking if the added post values are equal to the post or not
		assertEquals(100, addedPost.getPostId());
		assertEquals("Lucifer", addedPost.getTitle());
		assertEquals(PostType.VIDEO_IMAGE, addedPost.getContent());
		assertEquals("Deckerstar", addedPost.getFlair());
		assertEquals(10000, addedPost.getVotes());
		assertEquals(false, addedPost.isNotSafeForWork());
		assertEquals(true, addedPost.isOriginalContent());
		assertEquals(true, addedPost.isSpoiler());
		assertEquals(false, addedPost.isVoteUp());
		assertEquals(2, addedPost.getComments().size());
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

		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(16);
		commentIds.add(17);
				
		updatedPost.setCommentIds(commentIds);
		
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
		
		Comment comment1 = new Comment();
		comment1.setCommentId(16);
		comment1.setCommentDescription("Awesome");
		comment1.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(16)).thenReturn(Optional.of(comment1));
		
		Comment comment2 = new Comment();
		comment2.setCommentId(17);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(17)).thenReturn(Optional.of(comment2));
		
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);
		
		post.setComments(comments);
		
		// Sending the post object when the following functions are called instead of using database
		Mockito.when(postRepo.findById(59)).thenReturn(Optional.of(post));
		Mockito.when(postRepo.save(post)).thenReturn(post);
		
		PostOutputDto updatedPostOutput = postServ.updatePost(updatedPost);
		
		// checking if the updated post values are equal to the post or not
		assertEquals(59, updatedPostOutput.getPostId());
		assertEquals("Game of Thrones", updatedPostOutput.getTitle());
		assertEquals(PostType.LINK, updatedPostOutput.getContent());
		assertEquals("GameOfThrones", updatedPostOutput.getFlair());
		assertEquals(234578, updatedPostOutput.getVotes());
		assertEquals(false, updatedPostOutput.isNotSafeForWork());
		assertEquals(true, updatedPostOutput.isOriginalContent());
		assertEquals(true, updatedPostOutput.isSpoiler());
		assertEquals(true, updatedPostOutput.isVoteUp());
		assertEquals(2, updatedPostOutput.getComments().size());
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
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(16);
		commentIds.add(17);
						
		deletedPost.setCommentIds(commentIds);

		// Creating post object
		Post post = new Post();
		
		// Setting the post values
		post.setPostId(deletedPost.getPostId());
		post.setTitle(deletedPost.getTitle());
		post.setContent(deletedPost.getContent());
		post.setCreatedDateTime(deletedPost.getCreatedDateTime());
		post.setFlair('#' + deletedPost.getFlair());
		post.setNotSafeForWork(deletedPost.isNotSafeForWork());
		post.setOriginalContent(deletedPost.isOriginalContent());
		post.setVotes(deletedPost.getVotes());
		post.setVoteUp(deletedPost.isVoteUp());
		post.setSpoiler(deletedPost.isSpoiler());
		
		Comment comment1 = new Comment();
		comment1.setCommentId(16);
		comment1.setCommentDescription("Awesome");
		comment1.setVotes(10);
		
		Comment comment2 = new Comment();
		comment2.setCommentId(17);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);
		
		post.setComments(comments);
		
		// Sending post object when findById function is called
		Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post));
		
		// delete has void return type so do nothing is used
		doNothing().when(postRepo).delete(post);
		
		PostOutputDto deletedPostOutput = postServ.deletePost(100);
		
		// checking if the added post values are equal to the post or not
		assertEquals(100, deletedPostOutput.getPostId());
		assertEquals("Lucifer", deletedPostOutput.getTitle());
		assertEquals(PostType.VIDEO_IMAGE, deletedPostOutput.getContent());
		assertEquals("Deckerstar", deletedPostOutput.getFlair());
		assertEquals(10000, deletedPostOutput.getVotes());
		assertEquals(false, deletedPostOutput.isNotSafeForWork());
		assertEquals(true, deletedPostOutput.isOriginalContent());
		assertEquals(true, deletedPostOutput.isSpoiler());
		assertEquals(false, deletedPostOutput.isVoteUp());	
		assertEquals(2, deletedPostOutput.getComments().size());
		
	}
}
