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

@SpringBootTest
class BloggerServiceTest {

	@Autowired
	IBloggerService bloggerSer;

	@Test
	@Disabled
	void addBloggerTest(){
		
		// Creating blogger object and setting values
		BloggerInputDto blogger = new BloggerInputDto();
		blogger.setBloggerName("TestDemo");
		
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(10);

		blogger.setCommunityIds(communityIds);
		
		List<Integer> awards = new ArrayList<>();
		awards.add(8);
		blogger.setAwardsIds(awards);
		
		blogger.setUserId(4);
		
		// Calling addBlogger function in bloggerservice
		BloggerDto newBlog = bloggerSer.addBloggerDto(blogger);
		
		// Comparing both the blogger values
		assertEquals("TestDemo", newBlog.getBloggerName());
		assertEquals(1, newBlog.getCommunities().size());
		assertEquals(4, newBlog.getUser().getUserId());
	}

	@Test
	void updateBloggerTest(){
		
		BloggerInputDto blogger = new BloggerInputDto();
		blogger.setBloggerId(24);
		blogger.setBloggerName("updateTestDemo");
		
		// Storing community ids in a list of integers
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(10);
		
		blogger.setCommunityIds(communityIds);
		
		List<Integer> awards = new ArrayList<>();
		awards.add(9);
		blogger.setAwardsIds(awards);
		
		blogger.setUserId(2);
		
		BloggerDto updatedBlog = bloggerSer.updateBlogger(blogger);
		
		assertEquals(24, updatedBlog.getBloggerId());
		assertEquals("updateTestDemo", updatedBlog.getBloggerName());
		assertEquals(1, updatedBlog.getCommunities().size());
		assertEquals(2, updatedBlog.getUser().getUserId());

	}

	@Test
	void viewBloggerTest(){
		BloggerOutputDto blogger = bloggerSer.viewBlogger(15);
		assertEquals("updateTestDemo", blogger.getBloggerName());
		assertEquals(50, blogger.getKarma());

	}

	@Test
	void viewAllBloggersTest() {
		List<BloggerOutputDto> bloggers = bloggerSer.viewAllBloggers();
		int noOfBloggers = bloggers.size();
		assertEquals(3, noOfBloggers);
	}

	@Test
	void viewBloggerListByCommunityIdTest(){
		
		List<BloggerOutputDto> bloggers = bloggerSer.viewBloggerListByCommunityId(11);
		
		assertEquals(1, bloggers.size());
	}
	
	@Test
	void getBloggerByAwardIdTest(){

		List<BloggerOutputDto> bloggers = bloggerSer.getBloggerByAwardId(6);

		assertEquals(1, bloggers.size());

	}
	
	@Test
	void getBloggerByUserIdTest() {
		BloggerOutputDto  blogger = bloggerSer.getBloggerByUserId(1);
		assertEquals(15, blogger.getBloggerId());
		assertEquals("updateTestDemo", blogger.getBloggerName());
		assertEquals(50, blogger.getKarma());
	}
}