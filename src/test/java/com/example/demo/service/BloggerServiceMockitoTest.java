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
import com.example.demo.dto.CommentInputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Comment;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommentRepository;

import org.mockito.BDDMockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BloggerServiceMockitoTest {
	
	// @InjectMocks - Creates instance of a class and injects mocks that are created with @Mock
	@InjectMocks
	BloggerServiceImpl blogSer;

	// @MockBean - Creates Mock of a class or interface
	@MockBean
	IBloggerRepository blogRepo;

	@MockBean
	ICommentRepository comRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addBlogger() {
		// Creating blogger object
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setUserId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		Comment comment1 = new Comment();
		comment1.setCommentId(26);
		comment1.setCommentDescription("Awesome");
		comment1.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(26)).thenReturn(Optional.of(comment1));
		
		Comment comment2 = new Comment();
		comment2.setCommentId(27);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(27)).thenReturn(Optional.of(comment2));
		
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);
		
		blogger.setComments(comments);
		
		// Sending post object when save function is called
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);
		
		Blogger newBlog = blogSer.addBlogger(blogger);	

		// checking if the added post values are equal to the post or not
		assertEquals(1, newBlog.getUserId());
		assertEquals("Abc", newBlog.getBloggerName());
		assertEquals(20,newBlog.getKarma());
		assertEquals(2, newBlog.getComments().size());
	}
	
	@Test
	void addBloggerDtoTest() {
		// Creating BloggerInputDto object
		BloggerInputDto bloggerInput = new BloggerInputDto();
		
		// Setting the values
		bloggerInput.setUserId(140);
		bloggerInput.setBloggerName("Abcde");
		bloggerInput.setKarma(24);
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(19);
		commentIds.add(20);
		
		bloggerInput.setCommentIds(commentIds);
		
		// Creating blogger object
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setKarma(bloggerInput.getKarma());
		
		Comment comment1 = new Comment();
		comment1.setCommentId(26);
		comment1.setCommentDescription("Awesome");
		comment1.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(19)).thenReturn(Optional.of(comment1));
		
		Comment comment2 = new Comment();
		comment2.setCommentId(27);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(comRepo.findById(20)).thenReturn(Optional.of(comment2));

		// List to store comments 
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);

		blogger.setComments(comments);
		System.out.println(blogger);
		
		// Sending blogger object when save function is called
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);

		Blogger blogOutput = blogSer.addBloggerDto(bloggerInput);		
		
		// checking if the added blogger values are equal to the blogger or not
		assertEquals(140, blogOutput.getUserId());
		assertEquals("Abcde", blogOutput.getBloggerName());
		assertEquals(24,blogOutput.getKarma());
		assertEquals(2, blogOutput.getComments().size());
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
		
		BloggerInputDto bloggerInput = new BloggerInputDto();
		
		// Setting the values
		bloggerInput.setUserId(100);
		bloggerInput.setBloggerName("Abc");
		bloggerInput.setKarma(20);
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(16);
		
		bloggerInput.setCommentIds(commentIds);
		
		Blogger blogger = new Blogger();
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setKarma(bloggerInput.getKarma());
		
		// List to store comments 
		List<Comment> comments = new ArrayList<>();
		for(Integer id : bloggerInput.getCommentIds()) {
			
			// Creating comment object and setting values
			Comment newComment = new Comment();
			newComment.setCommentId(26);
			newComment.setCommentDescription("Awesome");
			newComment.setVotes(10);
			
			// adding new comments top comment list
		    comments.add(newComment);
		    
			// Returning newComment when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newComment));
		}
		
		blogger.setComments(comments);
		Mockito.when(blogRepo.findById(100)).thenReturn(Optional.of(blogger));

		// delete has void return type so do nothing is used
		doNothing().when(blogRepo).deleteById(blogger.getUserId());
		
		Blogger deletedBlogger = blogSer.deleteBlogger(bloggerInput);

		assertEquals(100, deletedBlogger.getUserId());
		assertEquals("Abc", deletedBlogger.getBloggerName());
		assertEquals(20, deletedBlogger.getKarma());
		assertEquals(1, deletedBlogger.getComments().size());
	}

	@Test
	public void updateBloggerTest() {
		
		BloggerInputDto bloggerInput = new BloggerInputDto();
		
		// Setting the values
		bloggerInput.setUserId(1001);
		bloggerInput.setBloggerName("Abc");
		bloggerInput.setKarma(20);
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(16);
		
		bloggerInput.setCommentIds(commentIds);
		
		Blogger blogger = new Blogger();
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setKarma(bloggerInput.getKarma());
		
		// List to store comments 
		List<Comment> comments = new ArrayList<>();
		for(Integer id : bloggerInput.getCommentIds()) {
			
			// Creating comment object and setting values
			Comment newComment = new Comment();
			newComment.setCommentId(26);
			newComment.setCommentDescription("Awesome");
			newComment.setVotes(10);
			
			// adding new comments top comment list
		    comments.add(newComment);
		    
			// Returning newComment when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newComment));
		}
		
		blogger.setComments(comments);
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);

		Blogger updatedBlogger = blogRepo.save(blogger);

		assertEquals(1001, updatedBlogger.getUserId());
		assertEquals("Abc", updatedBlogger.getBloggerName());
		assertEquals(20, updatedBlogger.getKarma());
		assertEquals(1, updatedBlogger.getComments().size());
	}

}
