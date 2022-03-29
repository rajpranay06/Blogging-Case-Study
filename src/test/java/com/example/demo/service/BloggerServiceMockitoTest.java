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

import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Award;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Coin;
import com.example.demo.bean.Comment;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommentRepository;

import org.mockito.BDDMockito;

import com.example.demo.bean.Community;
import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.repository.ICommunityRepository;
import com.example.demo.repository.IPostRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class BloggerServiceMockitoTest {
	
	// @InjectMocks - Creates instance of a class and injects mocks that are created with @Mock
	@InjectMocks
	BloggerServiceImpl blogSer;

	// @MockBean - Creates Mock of a class or interface
	@MockBean
	IBloggerRepository blogRepo;
	
	@MockBean
	ICommunityRepository comRepo;
	
	@MockBean
	IPostRepository postRepo;

	@MockBean
	ICommentRepository commentRepo;

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
		
		Comment comment2 = new Comment();
		comment2.setCommentId(27);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);
		
		blogger.setComments(comments);
		
		// Post
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
		
		List<Award> awards = new ArrayList<>();
		Award award = new Award();
		award.setAwardId(5);
		award.setCoin(Coin.GOLD);
		
		awards.add(award);
		
		newPost.setAwards(awards);
		
		List<Post> posts = new ArrayList<>();
		posts.add(newPost);
		
		blogger.setPosts(posts);
		
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
		
		List<Post> communityPosts = new ArrayList<Post>();
		
		Post post1 = new Post();
		post1.setPostId(100);
		post1.setTitle("Lucifer");
		post1.setContent(PostType.VIDEO_IMAGE);
		post1.setCreatedDateTime(LocalDateTime.now());
		post1.setFlair("Deckerstar");
		post1.setNotSafeForWork(false);
		post1.setOriginalContent(true);
		post1.setVotes(10000);
		post1.setVoteUp(false);
		post1.setSpoiler(true);
		
		post1.setAwards(awards);
		
		communityPosts.add(post1);
		
		Community com = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,posts);
		
		Community newCommunity = new Community();
		newCommunity.setCommunityId(com.getCommunityId());
		newCommunity.setCommunityDescription(com.getCommunityDescription());
		newCommunity.setTotalMembers(com.getTotalMembers());
		newCommunity.setOnlineMembers(com.getOnlineMembers());
		newCommunity.setImage(com.getImage());
		newCommunity.setCreatedOn(com.getCreatedOn());
		newCommunity.setPostRulesAllowed(com.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(com.getBanningPolicy());
		newCommunity.setFlairs(com.getFlairs());
		newCommunity.setPost(posts);
		
		List<Community> communities = new ArrayList<>();
		communities.add(newCommunity);
		
		blogger.setCommunities(communities);
		
		// Sending post object when save function is called
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);
		
		Blogger newBlog = blogSer.addBlogger(blogger);

		// checking if the added blogger values are equal to the newBlog or not
		assertEquals(1, newBlog.getUserId());
		assertEquals("Abc", newBlog.getBloggerName());
		assertEquals(20,newBlog.getKarma());
		assertEquals(2, newBlog.getComments().size());
		assertEquals(1, newBlog.getPosts().size());
		assertEquals(1, newBlog.getCommunities().size());
	}

	@Test
	void viewBloggerTest() throws IdNotFoundException {
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setUserId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		Comment comment1 = new Comment();
		comment1.setCommentId(26);
		comment1.setCommentDescription("Awesome");
		comment1.setVotes(10);
		
		Comment comment2 = new Comment();
		comment2.setCommentId(27);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);
		
		blogger.setComments(comments);
		
		// Post
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
		
		List<Award> awards = new ArrayList<>();
		Award award = new Award();
		award.setAwardId(5);
		award.setCoin(Coin.GOLD);
		
		awards.add(award);
		
		newPost.setAwards(awards);
		
		List<Post> posts = new ArrayList<>();
		posts.add(newPost);
		
		blogger.setPosts(posts);
		
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
	
		List<Post> communityPosts = new ArrayList<Post>();
		
		Post post1 = new Post();
		post1.setPostId(100);
		post1.setTitle("Lucifer");
		post1.setContent(PostType.VIDEO_IMAGE);
		post1.setCreatedDateTime(LocalDateTime.now());
		post1.setFlair("Deckerstar");
		post1.setNotSafeForWork(false);
		post1.setOriginalContent(true);
		post1.setVotes(10000);
		post1.setVoteUp(false);
		post1.setSpoiler(true);
		
		post1.setAwards(awards);
		
		communityPosts.add(post1);
		
		Community com = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,posts);
		
		Community newCommunity = new Community();
		newCommunity.setCommunityId(com.getCommunityId());
		newCommunity.setCommunityDescription(com.getCommunityDescription());
		newCommunity.setTotalMembers(com.getTotalMembers());
		newCommunity.setOnlineMembers(com.getOnlineMembers());
		newCommunity.setImage(com.getImage());
		newCommunity.setCreatedOn(com.getCreatedOn());
		newCommunity.setPostRulesAllowed(com.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(com.getBanningPolicy());
		newCommunity.setFlairs(com.getFlairs());
		newCommunity.setPost(posts);
		
		List<Community> communities = new ArrayList<>();
		communities.add(newCommunity);
		
		blogger.setCommunities(communities);
		
		// Sending post object when save function is called
		Mockito.when(blogRepo.findById(1)).thenReturn(Optional.of(blogger));
		
		BloggerOutputDto newBlog = blogSer.viewBlogger(1);

		// checking if the added blogger values are equal to the newBlog or not
		assertEquals(1, newBlog.getUserId());
		assertEquals("Abc", newBlog.getBloggerName());
		assertEquals(20,newBlog.getKarma());
	}
	
	@Test
	public void viewAllBloggers() {
		List<Blogger> bloggers = new ArrayList<>();
		bloggers.add(new Blogger());
		
		List<BloggerOutputDto> allBloggers = new ArrayList<>();
		allBloggers.add(new BloggerOutputDto());

		BDDMockito.given(blogRepo.findAll()).willReturn(bloggers);

		List<BloggerOutputDto> expected = blogSer.viewAllBloggers();

		assertEquals(expected, allBloggers);
		verify(blogRepo).findAll();
	}

	
	
	
	

}
