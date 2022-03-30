package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.example.demo.bean.Blogger;
import com.example.demo.bean.Comment;
import com.example.demo.bean.Community;
import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.bean.UserEntity;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.repository.IPostRepository;

@ExtendWith(SpringExtension.class)
class CommentServiceMockitoTest {

	@InjectMocks
	CommentServiceImpl comService;
	
	@MockBean
	ICommentRepository comRepo;
	
	@MockBean
	IPostRepository postRepo;
	
	@MockBean
	IBloggerRepository blogRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	void addCommentTest() {
		Comment comment = new Comment();
		comment.setCommentId(10);
		comment.setCommentDescription("test1");
		comment.setVotes(10);
		comment.setVoteUp(true);
		
		Post newPost = new Post();
		// Setting the values
		newPost.setPostId(100);
		newPost.setTitle("Lucifer");
		newPost.setContent(PostType.VIDEO_IMAGE);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("Deckerstar");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(10000);
		newPost.setVoteUp(false);
		newPost.setSpoiler(true);
		
		comment.setPost(newPost);
		
		// Creating blogger object
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setBloggerId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		// Community 
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("SportsNews");
		
		// Creating community using constructor
		Community com = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f);
	
		List<Community> communities = new ArrayList<>();
		communities.add(com);
		
		blogger.setCommunities(communities);
		
		// Creating and setting the user
		UserEntity user = new UserEntity(5,"ram@gmail.com","ram@1234","Trader",false);
		blogger.setUser(user);
		
		comment.setBlogger(blogger);
		
		Mockito.when(comRepo.save(comment)).thenReturn(comment);
		
		CommentDto newComment = comService.addComment(comment);
		
		assertEquals(10, newComment.getCommentId());
		assertEquals("test1", newComment.getCommentDescription());
		assertEquals(10, newComment.getVotes());
		assertEquals(true, newComment.isVoteUp());
		assertEquals(100, newComment.getPost().getPostId());
		assertEquals(1, newComment.getBlogger().getBloggerId());
	}
	
	
	@Test
	void updateCommentTest() {
		
		CommentInputDto com = new CommentInputDto();
		com.setCommentId(10);
		com.setCommentDescription("test1");
		com.setPostId(100);
		com.setBloggerId(1);
		com.setVotes(10);
		com.setVoteUp(true);
		
		Comment comment = new Comment();
		comment.setCommentId(com.getCommentId());
		comment.setCommentDescription(com.getCommentDescription());
		comment.setVotes(com.getVotes());
		comment.setVoteUp(com.isVoteUp());
		
		Post newPost = new Post();
		// Setting the values
		newPost.setPostId(100);
		newPost.setTitle("Lucifer");
		newPost.setContent(PostType.VIDEO_IMAGE);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("Deckerstar");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(10000);
		newPost.setVoteUp(false);
		newPost.setSpoiler(true);
		
		comment.setPost(newPost);
		
		// Creating blogger object
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setBloggerId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		// Community 
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("SportsNews");
		
		// Creating community using constructor
		Community community = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f);
	
		List<Community> communities = new ArrayList<>();
		communities.add(community);
		
		blogger.setCommunities(communities);
		
		// Creating and setting the user
		UserEntity user = new UserEntity(5,"ram@gmail.com","ram@1234","Trader",false);
		blogger.setUser(user);
		
		comment.setBlogger(blogger);
		
		Mockito.when(comRepo.findById(10)).thenReturn(Optional.of(comment));
		
		Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(newPost));
		
		Mockito.when(blogRepo.findById(1)).thenReturn(Optional.of(blogger));

		Mockito.when(comRepo.save(comment)).thenReturn(comment);
		
		CommentOutputDto newComment = comService.updateComment(com);
		
		assertEquals(10, newComment.getCommentId());
		assertEquals("test1", newComment.getCommentDescription());
		assertEquals(10, newComment.getVotes());
		assertEquals(true, newComment.isVoteUp());
	}
	
	@Test
	void getCommentByIdTest() {
		Comment comment = new Comment();
		comment.setCommentId(10);
		comment.setCommentDescription("test1");
		comment.setVotes(10);
		comment.setVoteUp(true);
		
		Post newPost = new Post();
		// Setting the values
		newPost.setPostId(100);
		newPost.setTitle("Lucifer");
		newPost.setContent(PostType.VIDEO_IMAGE);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("Deckerstar");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(10000);
		newPost.setVoteUp(false);
		newPost.setSpoiler(true);
		
		comment.setPost(newPost);
		
		// Creating blogger object
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setBloggerId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		// Community 
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("SportsNews");
		
		// Creating community using constructor
		Community community = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f);
	
		List<Community> communities = new ArrayList<>();
		communities.add(community);
		
		blogger.setCommunities(communities);
		
		// Creating and setting the user
		UserEntity user = new UserEntity(5,"ram@gmail.com","ram@1234","Trader",false);
		blogger.setUser(user);
		
		comment.setBlogger(blogger);
		
		Mockito.when(comRepo.findById(10)).thenReturn(Optional.of(comment));
		
		CommentOutputDto com = comService.getCommentById(10);
		
		assertEquals(10, com.getCommentId());
		assertEquals("test1", com.getCommentDescription());
		assertEquals(10, com.getVotes());
		assertEquals(true, com.isVoteUp());
		
	}
	
	@Test
	void getCommentsByPostIdTest() {
		Comment comment = new Comment();
		comment.setCommentId(10);
		comment.setCommentDescription("test1");
		comment.setVotes(10);
		comment.setVoteUp(true);
		
		Post newPost = new Post();
		// Setting the values
		newPost.setPostId(100);
		newPost.setTitle("Lucifer");
		newPost.setContent(PostType.VIDEO_IMAGE);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("Deckerstar");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(10000);
		newPost.setVoteUp(false);
		newPost.setSpoiler(true);
		
		comment.setPost(newPost);
		
		// Creating blogger object
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setBloggerId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		// Community 
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("SportsNews");
		
		// Creating community using constructor
		Community com = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f);
	
		List<Community> communities = new ArrayList<>();
		communities.add(com);
		
		blogger.setCommunities(communities);
		
		// Creating and setting the user
		UserEntity user = new UserEntity(5,"ram@gmail.com","ram@1234","Trader",false);
		blogger.setUser(user);
		
		comment.setBlogger(blogger);
		
		List<Comment> clist = new ArrayList<>();
		
		clist.add(comment);
		
		Mockito.when(comRepo.getAllCommentsOfPost(100)).thenReturn(clist);
		
		List<CommentOutputDto> comList = comService.listAllCommentsOfPost(100);
		
		assertEquals(1, comList.size());

		
	}
	
	@Test
	void getCommentsByBloggerIdTest() {
		Comment comment = new Comment();
		comment.setCommentId(10);
		comment.setCommentDescription("test1");
		comment.setVotes(10);
		comment.setVoteUp(true);
		
		Post newPost = new Post();
		// Setting the values
		newPost.setPostId(100);
		newPost.setTitle("Lucifer");
		newPost.setContent(PostType.VIDEO_IMAGE);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("Deckerstar");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(10000);
		newPost.setVoteUp(false);
		newPost.setSpoiler(true);
		
		comment.setPost(newPost);
		
		List<Comment> clist = new ArrayList<>();
		
		clist.add(comment);
		
		Mockito.when(comRepo.getCommentsByBlogger(1)).thenReturn(clist);
		
		List<CommentOutputDto> comList = comService.listAllCommentsOfBlogger(1);
		
		assertEquals(1, comList.size());
	}
	
}
