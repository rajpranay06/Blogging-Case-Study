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


	void addBloggerTest() throws IdNotFoundException {

		// Creating blogger object and setting values
		BloggerInputDto blogger = new BloggerInputDto();
		blogger.setBloggerName("TestDemo");

		blogger.setKarma(3);

		// Calling addBlogger function in bloggerservice
		Blogger newBlog = bloggerSer.addBlogger(blogger);


		
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(19);
		List<Integer> postIds = new ArrayList<>();
		postIds.add(17);

		blogger.setCommunityIds(communityIds);
		blogger.setPostIds(postIds);
		
		// Calling addBlogger function in bloggerservice
		BloggerDto newBlog = bloggerSer.addBloggerDto(blogger);
		

		// Comparing both the blogger values
		assertEquals("TestDemo", newBlog.getBloggerName());
		assertEquals(1, newBlog.getCommunities().size());
		assertEquals(1, newBlog.getPosts().size());
	}

	@Test

	@Disabled
	void addBloggerDtoTest() {
		// Creating BloggerInputDto object
		// Creating bloggerInputDto object and setting values
		BloggerInputDto blogInputDto = new BloggerInputDto();

		// Setting the values
		blogInputDto.setBloggerName("TestdemoDto");
		blogInputDto.setKarma(3);

		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(26);
		commentIds.add(27);

		blogInputDto.setCommentIds(commentIds);

		// Adding the blogger
		Blogger blogOutputDto = bloggerSer.addBloggerDto(blogInputDto);

		// Checking if the added blogger values are equal to the blogger or not
		assertEquals("TestdemoDto", blogOutputDto.getBloggerName());
		assertEquals(3, blogOutputDto.getKarma());
		assertEquals(2, blogOutputDto.getComments().size());

		// Storing community ids in a list of integers
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(62);

		blogInputDto.setCommunityIds(communityIds);

		// Calling addBloggerDto function
		Blogger blog = bloggerSer.addBloggerDto(blogInputDto);

		// Comparing values
		assertEquals("TestdemoDto", blog.getBloggerName());
		assertEquals(3, blog.getKarma());
		assertEquals(1, blog.getCommunities().size());
	}

	@Test
	@Disabled
=======

	void updateBloggerTest() throws IdNotFoundException {
		
		BloggerInputDto blogger = new BloggerInputDto();
		blogger.setUserId(27);
		blogger.setBloggerName("updateTestDemo");

		blogger.setKarma(30);

		// Storing comment ids in a list of integers
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(62);

		blogger.setCommentIds(commentIds);
		// Storing community ids in a list of integers
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(62);

		blogger.setCommunityIds(communityIds);

		Blogger updatedBlog = bloggerSer.updateBlogger(blogger);

		assertEquals(69, updatedBlog.getUserId());

		
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
		assertEquals(1, updatedBlog.getComments().size());
		assertEquals(1, updatedBlog.getPosts().size());
		assertEquals(1, updatedBlog.getCommunities().size());

	}

	@Test

	@Disabled
	void deleteBloggerTest() throws IdNotFoundException {

		BloggerInputDto blogger = new BloggerInputDto();
		blogger.setUserId(69);
		blogger.setBloggerName("updateTestDemo");
		blogger.setKarma(30);

		// Storing comment ids in a list of integers
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(62);

		blogger.setCommentIds(commentIds);
		// Storing community ids in a list of integers
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(62);

		blogger.setCommunityIds(communityIds);

		bloggerSer.deleteBlogger(blogger.getUserId());

	}

	@Test
	@Disabled

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


		List<BloggerOutputDto> bloggers = bloggerSer.viewBloggerListByCommunityId(61);

		assertEquals(1, bloggers.size());
	}

	@Test
	void getBloggerByAwardIdTest() throws IdNotFoundException {

		List<BloggerOutputDto> bloggers = bloggerSer.getBloggerByAwardId(64);

		assertEquals(1, bloggers.size());

	}


		
		List<BloggerOutputDto> bloggers = bloggerSer.viewBloggerListByCommunityId(18);
		
		assertEquals(1, bloggers.size());
	}

}