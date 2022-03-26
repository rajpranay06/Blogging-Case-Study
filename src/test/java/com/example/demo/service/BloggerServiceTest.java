package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

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
		BloggerInputDto blogInputDto = new BloggerInputDto();
		blogInputDto.setBloggerName("TestdemoDto");
		blogInputDto.setKarma(3);

		BloggerOutputDto blogOutputDto = bloggerSer.addBloggerDto(blogInputDto);
		assertEquals("TestdemoDto", blogOutputDto.getBloggerName());
		assertEquals(3, blogOutputDto.getKarma());
	}

	@Test
	@Disabled
	void updateBloggerTest() throws IdNotFoundException {
		Blogger blogger = new Blogger();
		blogger.setUserId(38);
		blogger.setBloggerName("updateTestDemo");
		blogger.setKarma(3);
		Blogger updatedBlog = bloggerSer.updateBlogger(blogger);
		assertEquals(38, updatedBlog.getUserId());
		assertEquals("updateTestDemo", updatedBlog.getBloggerName());
		assertEquals(3, updatedBlog.getKarma());

	}

	@Test
	@Disabled
	void deleteBloggerTest() throws IdNotFoundException {

		Blogger blogger = bloggerSer.viewBlogger(38);
		Blogger deletedBlogger = bloggerSer.deleteBlogger(blogger);
		assertEquals(38, deletedBlogger.getUserId());
		assertEquals("updateTestDemo", deletedBlogger.getBloggerName());
		assertEquals(3, deletedBlogger.getKarma());

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
