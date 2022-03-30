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
		newPost.setTitle("Game of Thrones");
		newPost.setContent(PostType.LINK);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("GOT");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(67890);
		newPost.setVoteUp(true);
		newPost.setSpoiler(true);
		
		// Creating List of award ids
		List<Integer> awardIds = new ArrayList<>();
		awardIds.add(7);
		newPost.setAwardIds(awardIds);
		
		// Setting community ID
		newPost.setCommunityId(49);
		
		// Adding the post
		PostDto post = postServ.addPost(newPost);
		
		// checking if the added post values are equal to the post or not
		assertEquals("Game of Thrones", post.getTitle());
		assertEquals(PostType.LINK, post.getContent());
		assertEquals("GOT", post.getFlair());
		assertEquals(67890, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(true, post.isVoteUp());
		assertEquals(49,post.getCommunity().getCommunityId());
		
	}
	
	
	@Test
//	@Disabled
	void updatePostTest() {
		// Creating PostInputDto object
		PostInputDto updatedPost = new PostInputDto(); 
		
		// Setting the values
		updatedPost.setPostId(52);
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
		awardIds.add(7);
		updatedPost.setAwardIds(awardIds);
		
		// Setting community ID
		updatedPost.setCommunityId(49);
		
		// Updating the post
		PostDto post = postServ.updatePost(updatedPost);
		
		// checking if the added post values are equal to the post or not
		assertEquals(52, post.getPostId());
		assertEquals("Game of Thrones", post.getTitle());
		assertEquals(PostType.LINK, post.getContent());
		assertEquals("GOTTheEpic", post.getFlair());
		assertEquals(189000, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(true, post.isVoteUp());
		assertEquals(49, post.getCommunity().getCommunityId());
	}
	
	@Disabled
	@Test
	void deletePostTest() {
		
		// Deleting the post
		postServ.deletePost(13);
		
	}
	
	@Disabled
	@Test
	void getPostsBySearchStringTest() {
		
		// Getting the posts
		List<PostOutputDto> posts = postServ.getPostBySearchString("of");
		
		// checking the no of posts
		assertEquals(1, posts.size());
	}
	
	@Test
	@Disabled
	void getPostByawardIdTest() {
		List<PostOutputDto> posts = postServ.getPostByawardId(88);
		assertEquals(8, posts.size());
	}
	@Test
	@Disabled
	void listPostsByCommunityId()
	{
		List<PostOutputDto> posts = postServ.listPostsByCommunityId(362);
		assertEquals(1,posts.size());
	}
}
