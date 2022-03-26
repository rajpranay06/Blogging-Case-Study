package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.PostType;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;

@SpringBootTest
public class PostServiceTest {

	@Autowired
	IPostService postServ;
	
	@Disabled
	@Test
	void addPostTest() {
		
		// Creating PostInputDto object
		PostInputDto newPost = new PostInputDto(); 
		
		// Setting the values
		newPost.setTitle("Game of Thrones");
		newPost.setContent(PostType.LINK);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("GOT");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(67890);
		newPost.setVoteUp(true);
		newPost.setSpoiler(true);
		
		// Adding the post
		PostOutputDto post = postServ.addPost(newPost);
		
		// checking if the added post values are equal to the post or not
		assertEquals("Game of Thrones", post.getTitle());
		assertEquals(PostType.LINK, post.getContent());
		assertEquals("GOT", post.getFlair());
		assertEquals(67890, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(true, post.isVoteUp());
		
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
		updatedPost.setFlair("GOTTheEpic");
		updatedPost.setNotSafeForWork(false);
		updatedPost.setOriginalContent(true);
		updatedPost.setVotes(189000);
		updatedPost.setVoteUp(true);
		updatedPost.setSpoiler(true);
		
		// Updating the post
		PostOutputDto post = postServ.updatePost(updatedPost);
		
		// checking if the added post values are equal to the post or not
		assertEquals(59, post.getPostId());
		assertEquals("Game of Thrones", post.getTitle());
		assertEquals(PostType.LINK, post.getContent());
		assertEquals("GOTTheEpic", post.getFlair());
		assertEquals(189000, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(true, post.isVoteUp());
	}
	
	@Disabled
	@Test
	void deletePostTest() {
		
		// Deleting the post
		PostOutputDto deletedPost = postServ.deletePost(56);

		// checking if the deleted post values are equal to the post or not
		assertEquals(56, deletedPost.getPostId());
		assertEquals("Happy New Year", deletedPost.getTitle());
		assertEquals(PostType.TEXT, deletedPost.getContent());
		assertEquals("HappyNewYear", deletedPost.getFlair());
		assertEquals(67, deletedPost.getVotes());
		assertEquals(false, deletedPost.isNotSafeForWork());
		assertEquals(false, deletedPost.isOriginalContent());
		assertEquals(false, deletedPost.isSpoiler());
		assertEquals(true, deletedPost.isVoteUp());
		
	}
	
	@Test
	void getPostsBySearchStringTest() {
		
		// Getting the posts
		List<PostOutputDto> posts = postServ.getPostBySearchString("of");
		
		// checking the no of posts
		assertEquals(1, posts.size());
	}
	
}
