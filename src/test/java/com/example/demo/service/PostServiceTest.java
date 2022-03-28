package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
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
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(26);
		commentIds.add(27);
		
		newPost.setCommentIds(commentIds);
		
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
		assertEquals(2,post.getComments().size());
		
	}
	
	@Disabled
	@Test
	void updatePostTest() {
		// Creating PostInputDto object
		PostInputDto updatedPost = new PostInputDto(); 
		
		// Setting the values
		updatedPost.setPostId(13);
		updatedPost.setTitle("Game of Thrones");
		updatedPost.setContent(PostType.LINK);
		updatedPost.setCreatedDateTime(LocalDateTime.now());
		updatedPost.setFlair("GOTTheEpic");
		updatedPost.setNotSafeForWork(false);
		updatedPost.setOriginalContent(true);
		updatedPost.setVotes(189000);
		updatedPost.setVoteUp(true);
		updatedPost.setSpoiler(true);
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(16);
		commentIds.add(17);
				
		updatedPost.setCommentIds(commentIds);	
		
		// Updating the post
		PostOutputDto post = postServ.updatePost(updatedPost);
		
		// checking if the added post values are equal to the post or not
		assertEquals(13, post.getPostId());
		assertEquals("Game of Thrones", post.getTitle());
		assertEquals(PostType.LINK, post.getContent());
		assertEquals("GOTTheEpic", post.getFlair());
		assertEquals(189000, post.getVotes());
		assertEquals(false, post.isNotSafeForWork());
		assertEquals(true, post.isOriginalContent());
		assertEquals(true, post.isSpoiler());
		assertEquals(true, post.isVoteUp());
		assertEquals(2,post.getComments().size());
	}
	
	@Disabled
	@Test
	void deletePostTest() {
		
		// Deleting the post
		PostOutputDto deletedPost = postServ.deletePost(13);

		// checking if the deleted post values are equal to the post or not
		assertEquals(13, deletedPost.getPostId());
		assertEquals("Game of Thrones", deletedPost.getTitle());
		assertEquals(PostType.TEXT, deletedPost.getContent());
		assertEquals("GOTTheEpic", deletedPost.getFlair());
		assertEquals(189000, deletedPost.getVotes());
		assertEquals(false, deletedPost.isNotSafeForWork());
		assertEquals(true, deletedPost.isOriginalContent());
		assertEquals(true, deletedPost.isSpoiler());
		assertEquals(true, deletedPost.isVoteUp());
		assertEquals(2,deletedPost.getComments().size());
		
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
	void listPostsByCommunityId()
	{
		List<Post> posts = postServ.listPostsByCommunityId(362);
		assertEquals(1,posts.size());
	}
	
}
