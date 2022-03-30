package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.example.demo.bean.Award;
import com.example.demo.bean.Coin;
import com.example.demo.bean.Community;
import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.repository.IAwardRepository;
import com.example.demo.repository.ICommunityRepository;
import com.example.demo.repository.IPostRepository;

@ExtendWith(SpringExtension.class)
public class PostServiceMockitoTest {

//	// @InjectMocks - Creates instance of a class and injects mocks that are created with @Mock
	@InjectMocks
	PostServiceImpl postServ;
	
	@InjectMocks
	CommunityServiceImpl communityServ;
	
	// @MockBean - Creates Mock of a class or interface
	@MockBean
	IPostRepository postRepo;
	
	@MockBean
	IAwardRepository awardRepo;
	
	@MockBean
	ICommunityRepository communityRepo;
	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void addPost() {
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
		
		// Creating list of awards
		List<Award> awards = new ArrayList<>();
		Award award = new Award();
		award.setAwardId(5);
		award.setCoin(Coin.GOLD);
		
		awards.add(award);
		
		// Setting awards to post
		newPost.setAwards(awards);
		
		// Creating Community
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
		
		// Setting community to post
		newPost.setCommunity(com);
		
		// Sending post object when save function is called
		Mockito.when(postRepo.save(newPost)).thenReturn(newPost);
		
		Post addedPost = postServ.addPostWithoutDto(newPost);	

		// checking if the added post values are equal to the post or not
		assertEquals(100, addedPost.getPostId());
		assertEquals("Lucifer", addedPost.getTitle());
		assertEquals(PostType.VIDEO_IMAGE, addedPost.getContent());
		assertEquals("Deckerstar", addedPost.getFlair());
		assertEquals(10000, addedPost.getVotes());
		assertEquals(false, addedPost.isNotSafeForWork());
		assertEquals(true, addedPost.isOriginalContent());
		assertEquals(true, addedPost.isSpoiler());
		assertEquals(false, addedPost.isVoteUp());
		assertEquals(1, addedPost.getAwards().size());
		assertEquals(com, addedPost.getCommunity());
	}
	
	@Test
	void updatePostTest() {

		// Creating PostInputDto object
		PostInputDto updatedPost = new PostInputDto(); 
		
		// Setting the values
		updatedPost.setPostId(59);
		updatedPost.setTitle("Game of Thrones");
		updatedPost.setContent(PostType.LINK);
		updatedPost.setCreatedDateTime(LocalDateTime.now());
		updatedPost.setFlair("GameOfThrones");
		updatedPost.setNotSafeForWork(false);
		updatedPost.setOriginalContent(true);
		updatedPost.setVotes(234578);
		updatedPost.setVoteUp(true);
		updatedPost.setSpoiler(true);
		
		//Adding awardIds to list
		List<Integer> awardIds = new ArrayList<>();
		awardIds.add(88);
		updatedPost.setAwardIds(awardIds);
		
		// Setting community Id
		updatedPost.setCommunityId(12);
		
		// Creating post object
		Post post = new Post();
		
		// Setting the post values
		post.setPostId(updatedPost.getPostId());
		post.setTitle(updatedPost.getTitle());
		post.setContent(updatedPost.getContent());
		post.setCreatedDateTime(updatedPost.getCreatedDateTime());
		post.setFlair(updatedPost.getFlair());
		post.setNotSafeForWork(updatedPost.isNotSafeForWork());
		post.setOriginalContent(updatedPost.isOriginalContent());
		post.setVotes(updatedPost.getVotes());
		post.setVoteUp(updatedPost.isVoteUp());
		post.setSpoiler(updatedPost.isSpoiler());
		
		Award awards = new Award();
		awards.setAwardId(88);
		awards.setCoin(Coin.PLATINUM);
		
		// Mockito when findById is called for getting award
		Mockito.when(awardRepo.findById(88)).thenReturn(Optional.of(awards));
		
		List<Award> allAwards = new ArrayList<>();
		allAwards.add(awards);
		
		post.setAwards(allAwards);
		
		// Creating Community
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
		
		// Mockito when findById is called for getting community
		Mockito.when(communityRepo.findById(12)).thenReturn(Optional.of(com));
		
		// Setting community to post
		post.setCommunity(com);

		// Sending the post object when the following functions are called instead of using database
		Mockito.when(postRepo.findById(59)).thenReturn(Optional.of(post));
		Mockito.when(postRepo.save(post)).thenReturn(post);
		
		PostDto updatedPostOutput = postServ.updatePost(updatedPost);
		
		// checking if the updated post values are equal to the post or not
		assertEquals(59, updatedPostOutput.getPostId());
		assertEquals("Game of Thrones", updatedPostOutput.getTitle());
		assertEquals(PostType.LINK, updatedPostOutput.getContent());
		assertEquals("GameOfThrones", updatedPostOutput.getFlair());
		assertEquals(234578, updatedPostOutput.getVotes());
		assertEquals(false, updatedPostOutput.isNotSafeForWork());
		assertEquals(true, updatedPostOutput.isOriginalContent());
		assertEquals(true, updatedPostOutput.isSpoiler());
		assertEquals(true, updatedPostOutput.isVoteUp());
		assertEquals(com, updatedPostOutput.getCommunity());
	}
	
	@Test
	void getPostByawardIdTest() {
		PostInputDto newPost = new PostInputDto(); 
		
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
		
		//Adding awardIds to list
		List<Integer> awardIds = new ArrayList<>();
		awardIds.add(88);
		newPost.setAwardIds(awardIds);
		
		Post post = new Post();
		
		post.setPostId(newPost.getPostId());
		post.setTitle(newPost.getTitle());
		post.setContent(newPost.getContent());
		post.setCreatedDateTime(newPost.getCreatedDateTime());
		post.setFlair(newPost.getFlair());
		post.setNotSafeForWork(newPost.isNotSafeForWork());
		post.setOriginalContent(newPost.isOriginalContent());
		post.setVotes(newPost.getVotes());
		post.setVoteUp(newPost.isVoteUp());
		post.setSpoiler(newPost.isSpoiler());
		
		List<Post> posts = new ArrayList<>();
		posts.add(post);
				
		Mockito.when(postRepo.getAllPostsByAwardId(88)).thenReturn(posts);
		
		List<PostOutputDto> allPost = postServ.getPostByawardId(88);
		
		assertEquals(1, allPost.size());
		
	}
	
	@Test
	void listPostsByCommunityId()
	{
		List<Post> posts = new ArrayList<Post>();
		
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
		
		posts.add(post1);
		
		Mockito.when(postRepo.getAllPostsByCommunityId(12)).thenReturn(posts);
		
		List<PostOutputDto> postslist = postServ.listPostsByCommunityId(12);
		assertEquals(1,postslist.size());
		
	}
	
	@Test
	void listPostsByAwardId()
	{
		List<Post> posts = new ArrayList<Post>();
		
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
		
		posts.add(post1);
		
		Mockito.when(postRepo.getAllPostsByAwardId(10)).thenReturn(posts);
		
		List<PostOutputDto> postslist = postServ.getPostByawardId(10);
		assertEquals(1,postslist.size());
		
	}
}
