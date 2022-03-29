package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.exception.IdNotFoundException;

@SpringBootTest
class BloggerServiceTest {

	@Autowired
	IBloggerService bloggerSer;

	@Test
	@Disabled
	void addBloggerTest() {
		
		// Creating blogger object and setting values
		Blogger blogger = new Blogger();
		blogger.setBloggerName("TestDemo");
		blogger.setKarma(3);
		
		// Calling addBlogger function in bloggerservice
		Blogger newBlog = bloggerSer.addBlogger(blogger);
		
		// Comparing both the blogger values
		assertEquals("TestDemo", newBlog.getBloggerName());
		assertEquals(3, newBlog.getKarma());

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
		assertEquals(2,blogOutputDto.getComments().size());
		
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
	void updateBloggerTest() throws IdNotFoundException {
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
		
		Blogger updatedBlog = bloggerSer.updateBlogger(blogger);
		
		assertEquals(69, updatedBlog.getUserId());
		assertEquals("updateTestDemo", updatedBlog.getBloggerName());
		assertEquals(30, updatedBlog.getKarma());
		assertEquals(1, updatedBlog.getComments().size());

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
		Blogger blogger = bloggerSer.viewBlogger(36);
		assertEquals("Tulsi", blogger.getBloggerName());
		assertEquals(4, blogger.getKarma());

	}

	@Test
	@Disabled
	void viewAllBloggersTest() {
		List<Blogger> bloggers = bloggerSer.viewAllBloggers();
		int noOfBloggers = bloggers.size();
		assertEquals(8, noOfBloggers);
	}

	@Test
	void viewBloggerListByCommunityIdTest() throws IdNotFoundException {
		
		List<BloggerOutputDto> bloggers = bloggerSer.viewBloggerListByCommunityId(61);
		
		assertEquals(1, bloggers.size());
	}


}