package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.PostType;
import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;

@SpringBootTest
public class PostServiceTest {

	@Autowired
	IPostService postServ;
	
	@Test
	@Disabled
	void addPostTest() {
		
		// Creating PostInputDto object
		PostInputDto newPost = new PostInputDto(); 
		
		// Setting the values
		newPost.setTitle("Friends");
		newPost.setContent(PostType.LINK);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("friends");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(67890);
		newPost.setVoteUp(true);
		newPost.setSpoiler(true);
		
		// Creating List of award ids
		List<Integer> awardIds = new ArrayList<>();
		awardIds.add(22);
		newPost.setAwardIds(awardIds);
		
		// Setting community ID
		newPost.setCommunityId(23);
		
		// Setting Blogger Id
		newPost.setBloggerId(24);
		
		// Adding the post
		PostDto post = postServ.addPost(newPost);
		
		// checking if the added post values are equal to the post or not
		assertEquals("Friends", post.getTitle());
		assertEquals(PostType.LINK, post.getContent());
		assertEquals("friends", post.getFlair());
		assertEquals(67890, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(true, post.isVoteUp());
		assertEquals(23,post.getCommunity().getCommunityId());
		assertEquals(24,post.getBlogger().getBloggerId());
	}
	
	
	@Test
	void updatePostTest() {
		// Creating PostInputDto object
		PostInputDto updatedPost = new PostInputDto(); 
		
		// Setting the values
		updatedPost.setPostId(25);
		updatedPost.setTitle("Game of Thrones");
		updatedPost.setContent(PostType.LINK);
		updatedPost.setCreatedDateTime(LocalDateTime.now());
		updatedPost.setFlair("GOTTheEpic");
		updatedPost.setNotSafeForWork(false);
		updatedPost.setOriginalContent(true);
		updatedPost.setVotes(189000);
		updatedPost.setVoteUp(true);
		updatedPost.setSpoiler(true);
		
		// Setting list of award ids
		List<Integer> awardIds = new ArrayList<>();
		awardIds.add(22);
		updatedPost.setAwardIds(awardIds);
		
		// Setting community ID
		updatedPost.setCommunityId(23);
		
		// Setting Blogger Id
		updatedPost.setBloggerId(24);
		
		// Updating the post
		PostDto post = postServ.updatePost(updatedPost);
		
		// checking if the added post values are equal to the post or not
		assertEquals(25, post.getPostId());
		assertEquals("Game of Thrones", post.getTitle());
		assertEquals(PostType.LINK, post.getContent());
		assertEquals("GOTTheEpic", post.getFlair());
		assertEquals(189000, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(true, post.isVoteUp());
		assertEquals(23, post.getCommunity().getCommunityId());
		assertEquals(24,post.getBlogger().getBloggerId());
	}
	
	@Test
	void getPostsBySearchStringTest() {
		
		// Getting the posts
		List<PostOutputDto> posts = postServ.getPostBySearchString("ost");
		
		// checking the no of posts
		assertEquals(1, posts.size());
	}
	
	@Test
	void getPostByawardIdTest() {
		List<PostOutputDto> posts = postServ.getPostByawardId(7);
		assertEquals(1, posts.size());
	}
	@Test
	void listPostsByCommunityId()
	{
		List<PostOutputDto> posts = postServ.listPostsByCommunityId(13);
		assertEquals(1,posts.size());
	}
	
	@Test
	void getPostByCommentId()
	{
		PostOutputDto post = postServ.getPostByCommentId(17);
		// checking if the added post values are equal to the post or not
		assertEquals(16, post.getPostId());
		assertEquals("Updated Post 1", post.getTitle());
		assertEquals(PostType.TEXT, post.getContent());
		assertEquals("updatedtest1", post.getFlair());
		assertEquals(190, post.getVotes());
		assertEquals(true, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(true, post.isVoteUp());
	}
	
	@Test
	void listPostsByBloggerId()
	{
		List<PostOutputDto> posts = postServ.getPostsByBlogger(15);
		// checking if the added post values are equal to the post or not
		assertEquals(1,posts.size());
	}
}
