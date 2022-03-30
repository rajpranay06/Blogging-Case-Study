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
		
		Community com = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f);
		
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
		
		Community com = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f);
		
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

		assertEquals(expected.size(), allBloggers.size());
		verify(blogRepo).findAll();
	}

	

	@Test
	void viewBloggerListByCommunityIdTest() throws IdNotFoundException {

		Blogger blogger1 = new Blogger();
		blogger1.setUserId(25);
		blogger1.setBloggerName("Ross");
		blogger1.setKarma(50);
		
		Blogger blogger2 = new Blogger();
		blogger2.setUserId(26);
		blogger2.setBloggerName("Joey");
		blogger2.setKarma(500);
		
		List<Blogger> bloggers = new ArrayList<>();
		
		Mockito.when(blogRepo.viewBloggerListByCommunityId(61)).thenReturn(bloggers);
		
		List<BloggerOutputDto> allBloggers = blogSer.viewBloggerListByCommunityId(61);
		
		assertEquals(2, allBloggers.size());
	}
	
	

	


}
