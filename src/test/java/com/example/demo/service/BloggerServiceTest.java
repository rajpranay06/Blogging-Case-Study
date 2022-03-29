package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BloggerDto;
import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.exception.IdNotFoundException;

@SpringBootTest
class BloggerServiceTest {

	@Autowired
	IBloggerService bloggerSer;

	@Test
	@Disabled
	void addBloggerTest() {
		
		// Creating blogger object and setting values
		BloggerInputDto blogger = new BloggerInputDto();
		blogger.setBloggerName("TestDemo");
		blogger.setKarma(3);
		
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(19);
		List<Integer> postIds = new ArrayList<>();
		postIds.add(17);
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(21);
		
		blogger.setCommentIds(commentIds);
		blogger.setCommunityIds(communityIds);
		blogger.setPostIds(postIds);
		
		// Calling addBlogger function in bloggerservice
		BloggerDto newBlog = bloggerSer.addBloggerDto(blogger);
		
		// Comparing both the blogger values
		assertEquals("TestDemo", newBlog.getBloggerName());
		assertEquals(3, newBlog.getKarma());
		assertEquals(1, newBlog.getComments().size());
		assertEquals(1, newBlog.getCommunities().size());
		assertEquals(1, newBlog.getPosts().size());
	}

	@Test
	void updateBloggerTest() throws IdNotFoundException {
		
		BloggerInputDto blogger = new BloggerInputDto();
		blogger.setUserId(27);
		blogger.setBloggerName("updateTestDemo");
		blogger.setKarma(30);
		
		// Storing comment ids in a list of integers
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(21);
		
		blogger.setCommentIds(commentIds);
		// Storing community ids in a list of integers
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(19);
		
		blogger.setCommunityIds(communityIds);
		
		List<Integer> postIds = new ArrayList<>();
		postIds.add(17);
		
		blogger.setPostIds(postIds);
		
		BloggerDto updatedBlog = bloggerSer.updateBlogger(blogger);
		
		assertEquals(27, updatedBlog.getUserId());
		assertEquals("updateTestDemo", updatedBlog.getBloggerName());
		assertEquals(30, updatedBlog.getKarma());
		assertEquals(1, updatedBlog.getComments().size());
		assertEquals(1, updatedBlog.getPosts().size());
		assertEquals(1, updatedBlog.getCommunities().size());

	}

	@Test
	void viewBloggerTest() throws IdNotFoundException {
		BloggerOutputDto blogger = bloggerSer.viewBlogger(13);
		assertEquals("Blogger Test 1", blogger.getBloggerName());
		assertEquals(80, blogger.getKarma());

	}

	@Test
	void viewAllBloggersTest() {
		List<BloggerOutputDto> bloggers = bloggerSer.viewAllBloggers();
		int noOfBloggers = bloggers.size();
		assertEquals(5, noOfBloggers);
	}

	@Test
	void viewBloggerListByCommunityIdTest() throws IdNotFoundException {
		
		List<BloggerOutputDto> bloggers = bloggerSer.viewBloggerListByCommunityId(18);
		
		assertEquals(1, bloggers.size());
	}

}