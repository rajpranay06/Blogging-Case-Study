package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.dto.CommunityInputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Community;
import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommunityRepository;
import com.example.demo.repository.IPostRepository;

import org.mockito.BDDMockito;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class BloggerServiceMockitoTest {

	@InjectMocks
	BloggerServiceImpl blogSer;

	@MockBean
	IBloggerRepository blogRepo;
	
	@MockBean
	ICommunityRepository comRepo;
	
	@MockBean
	IPostRepository postRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addBloggerDtoTest() {

		
		List<Integer> list=new ArrayList<>();
		list.add(13);
		list.add(17);
		list.add(19);
		
		BloggerInputDto bloggerInput = new BloggerInputDto("Kevin", 50, list);
		// Storing community ids
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(61);
		BloggerInputDto bloggerInput = new BloggerInputDto(89,"Mockadd", 4, communityIds);

		Blogger blogger = new Blogger();
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setKarma(bloggerInput.getKarma());
		
		// List to store community 
		List<Community> communities = new ArrayList<>();
		
		for(Integer id : bloggerInput.getCommunityIds()) {
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
			
			List<Integer> p = new ArrayList<Integer>();
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
			
			Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post1));
			posts.add(post1);
			p.add(post1.getPostId());
			
			// Creating communityDto object using constructor
			CommunityInputDto com = new CommunityInputDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,p);
			
			// Creating community object and setting values
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
			
			// adding new communities top community list
			communities.add(newCommunity);
			
			// Returning newCommunity when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newCommunity));
			
		}
		blogger.setCommunities(communities);
		
		// Returning blogger when save is called
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);
		BloggerOutputDto blogOutput = blogSer.addBloggerDto(bloggerInput);
		assertEquals("Kevin", blogOutput.getBloggerName());
		assertEquals(50, blogOutput.getKarma());
		Blogger blogOutput = blogSer.addBloggerDto(bloggerInput);
		
		assertEquals(89, blogOutput.getUserId());
		assertEquals("Mockadd", blogOutput.getBloggerName());
		assertEquals(4, blogOutput.getKarma());
		assertEquals(1, blogOutput.getCommunities().size());
		

	}

	@Test
	void viewBloggerTest() throws IdNotFoundException {
		// Storing community ids
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(61);
		BloggerInputDto bloggerInput = new BloggerInputDto(89,"Mockadd", 4, communityIds);

		Blogger blogger = new Blogger();
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setKarma(bloggerInput.getKarma());
		
		// List to store community 
		List<Community> communities = new ArrayList<>();
		
		for(Integer id : bloggerInput.getCommunityIds()) {
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
			
			List<Integer> p = new ArrayList<Integer>();
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
			
			Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post1));
			posts.add(post1);
			p.add(post1.getPostId());
			
			
			// Creating communityDto object using constructor
			CommunityInputDto com = new CommunityInputDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,p);
			
			// Creating community object and setting values
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
			
			// adding new communities top community list
			communities.add(newCommunity);
			
			// Returning newCommunity when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newCommunity));
			
		}
		blogger.setCommunities(communities);

		Mockito.when(blogRepo.findById(89)).thenReturn(Optional.of(blogger));

		Blogger blog = blogSer.viewBlogger(89);
		
		assertEquals(89, blog.getUserId());
		assertEquals("Mockadd", blog.getBloggerName());
		assertEquals(4, blog.getKarma());
		assertEquals(1, blog.getCommunities().size());

	}

	@Test
	public void viewAllBloggers() {
		List<Blogger> bloggers = new ArrayList<>();
		bloggers.add(new Blogger());

		BDDMockito.given(blogRepo.findAll()).willReturn(bloggers);

		List<Blogger> expected = blogSer.viewAllBloggers();

		assertEquals(expected, bloggers);
		verify(blogRepo).findAll();
	}

	@Test
	public void deleteBloggerTest() throws IdNotFoundException {
		// Storing community ids
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(61);
		BloggerInputDto bloggerInput = new BloggerInputDto(89,"Mockadd", 4, communityIds);

		Blogger blogger = new Blogger();
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setKarma(bloggerInput.getKarma());
		
		// List to store community 
		List<Community> communities = new ArrayList<>();
		
		for(Integer id : bloggerInput.getCommunityIds()) {
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
			
			List<Integer> p = new ArrayList<Integer>();
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
			
			Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post1));
			posts.add(post1);
			p.add(post1.getPostId());
			
			
			// Creating communityDto object using constructor
			CommunityInputDto com = new CommunityInputDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,p);
			
			// Creating community object and setting values
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
			
			// adding new communities top community list
			communities.add(newCommunity);
			
			// Returning newCommunity when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newCommunity));
			
		}
		blogger.setCommunities(communities);

		Mockito.when(blogRepo.findById(89)).thenReturn(Optional.of(blogger));

		// delete has void return type so do nothing is used
		doNothing().when(blogRepo).deleteById(blogger.getUserId());
		
		Blogger deletedBlogger = blogSer.deleteBlogger(bloggerInput);

		assertEquals(89, deletedBlogger.getUserId());
		assertEquals("Mockadd", deletedBlogger.getBloggerName());
		assertEquals(4, deletedBlogger.getKarma());
		assertEquals(1, deletedBlogger.getCommunities().size());
		
	}

	@Test
	public void updateBloggerTest() {
		// Storing community ids
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(61);
		BloggerInputDto bloggerInput = new BloggerInputDto(89,"Mockadd", 4, communityIds);

		Blogger blogger = new Blogger();
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setKarma(bloggerInput.getKarma());
		
		// List to store community 
		List<Community> communities = new ArrayList<>();
		
		for(Integer id : bloggerInput.getCommunityIds()) {
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
			
			List<Integer> p = new ArrayList<Integer>();
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
			
			Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post1));
			posts.add(post1);
			p.add(post1.getPostId());
			
			
			// Creating communityDto object using constructor
			CommunityInputDto com = new CommunityInputDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,p);
			
			// Creating community object and setting values
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
			
			// adding new communities top community list
			communities.add(newCommunity);
			
			// Returning newCommunity when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newCommunity));
			
		}
		blogger.setCommunities(communities);
		
		// Returning blogger when save is called
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);

		Blogger updateBlogger = blogRepo.save(blogger);
		
		assertEquals(89, updateBlogger.getUserId());
		assertEquals("Mockadd", updateBlogger.getBloggerName());
		assertEquals(4, updateBlogger.getKarma());
		assertEquals(1, updateBlogger.getCommunities().size());
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
