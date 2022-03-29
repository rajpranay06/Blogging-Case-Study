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
		
		Blogger blogger = new Blogger();
		blogger.setUserId(12);
		blogger.setBloggerName("TestDemo");
		blogger.setKarma(3);
		Blogger newBlog = bloggerSer.addBlogger(blogger);
		assertEquals(12, newBlog.getUserId());
		assertEquals("TestDemo", newBlog.getBloggerName());
		assertEquals(3, newBlog.getKarma());

	}

	@Test
	@Disabled
	void addBloggerDtoTest() {
		// Creating BloggerInputDto object
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
		
		Blogger updatedBlog = bloggerSer.updateBlogger(blogger);
		
		assertEquals(69, updatedBlog.getUserId());
		assertEquals("updateTestDemo", updatedBlog.getBloggerName());
		assertEquals(30, updatedBlog.getKarma());
		assertEquals(1, updatedBlog.getComments().size());
		
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
		
		Blogger deletedBlogger = bloggerSer.deleteBlogger(blogger);
		
		assertEquals(69, deletedBlogger.getUserId());
		assertEquals("updateTestDemo", deletedBlogger.getBloggerName());
		assertEquals(30, deletedBlogger.getKarma());
		

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

	// public List<Blogger> viewBloggerList(Community community);

	// public List<Customer> viewCustomerList(int theatreid);


}
