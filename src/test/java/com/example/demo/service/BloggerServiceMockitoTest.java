package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import org.mockito.BDDMockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BloggerServiceMockitoTest {

	@InjectMocks
	BloggerServiceImpl blogSer;

	@MockBean
	IBloggerRepository blogRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addBloggerDtoTest() {
		BloggerInputDto bloggerInput = new BloggerInputDto("Mockadd", 4);

		Blogger blogger = new Blogger();
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setKarma(bloggerInput.getKarma());

		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);

		BloggerOutputDto blogOutput = blogSer.addBloggerDto(bloggerInput);
		assertEquals("Mockadd", blogOutput.getBloggerName());
		assertEquals(4, blogOutput.getKarma());

	}

	@Test
	void viewBloggerTest() throws IdNotFoundException {
		Blogger blogger = new Blogger(37, "Rish", 3);
		Mockito.when(blogRepo.findById(37)).thenReturn(Optional.of(blogger));
		Blogger blg = blogSer.viewBlogger(37);
		assertEquals(37, blg.getUserId());
		assertEquals("Rish", blg.getBloggerName());
		assertEquals(3, blg.getKarma());

	}

	@Test
	public void viewAllBloggers() {
		List<Blogger> bloggers = new ArrayList();
		bloggers.add(new Blogger());

		BDDMockito.given(blogRepo.findAll()).willReturn(bloggers);

		List<Blogger> expected = blogSer.viewAllBloggers();

		assertEquals(expected, bloggers);
		verify(blogRepo).findAll();
	}

	@Test
	public void deleteBloggerTest() throws IdNotFoundException {
		Blogger blogger = new Blogger();
		blogger.setBloggerName("Test Name");
		blogger.setUserId(1);
		blogger.setKarma(2);
		when(blogRepo.findById(blogger.getUserId())).thenReturn(Optional.of(blogger));

		blogSer.deleteBlogger((blogger));
		verify(blogRepo).deleteById(blogger.getUserId());
	}

	@Test
	public void updateBloggerTest() {
		Blogger blogger = new Blogger();
		blogger.setUserId(38);
		blogger.setBloggerName("updateTestDemo");
		blogger.setKarma(3);

		when(blogRepo.save(any(Blogger.class))).thenReturn(blogger);

		Blogger updateBlogger = blogRepo.save(blogger);
		assertThat(updateBlogger.getBloggerName()).isNotNull();
	}

}
