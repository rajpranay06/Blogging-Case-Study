package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.Blogger;
import com.example.demo.bean.Community;
import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.dto.CommunityInputDto;


@SpringBootTest
public class CommunityServiceTest {
	@Autowired
	ICommunityService comServ;
	
	@Test
//	@Disabled
	void addCommunityTest()
	{
		//Create community object
		Community com = new Community();
		com.setCommunityId(1);
		com.setCommunityDescription("Humans");
		com.setTotalMembers(120);
		com.setOnlineMembers(110);
		//To set File
		File fw = new File("abc.jpg");
		com.setImage(fw);
		
		//To set created date
		com.setCreatedOn(LocalDate.parse("2019-02-07"));
		
		//To set list of post rules allowed
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		com.setPostRulesAllowed(glist);
		
		//To set list of post rules disallowed
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		com.setPostRulesDisAllowed(galist);
		
		//To set list of Banning policy
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		com.setBanningPolicy(bp);
		
		//To set list of flairs
		List<String> f = new ArrayList<String>();
		f.add("SportsNews");
		com.setFlairs(f);
		
		//To set List of postIds &  Posts 
		
		//List of PostIds
		List<Integer> p = new ArrayList<Integer>();
		//List of posts
		List<Post> posts = new ArrayList<Post>();
		
		//Creating post object
		Post post1 = new Post();
		//Adding data to post object
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
		p.add(post1.getPostId());
		com.setPost(posts);
		
		//Persist the community object to the DB using service implementation
		Community c = comServ.addCommunityWithoutDto(com);
		
		//Validate details
		assertEquals("Humans",c.getCommunityDescription());
		assertEquals(120,c.getTotalMembers());
		assertEquals(110,c.getOnlineMembers());
		assertEquals(fw,c.getImage());
		assertEquals(LocalDate.parse("2019-02-07"),c.getCreatedOn());
		assertEquals(glist,c.getPostRulesAllowed());
		assertEquals(galist,c.getPostRulesDisAllowed());
		assertEquals(bp,c.getBanningPolicy());
		assertEquals(f,c.getFlairs());
		assertEquals(1,c.getPost().size());
//		assertEquals(1,c.getBlogger().size());
	}
	
	@Test
//	@Disabled
	void updateCommunityTest()
	{
		Community com = new Community();
		com.setCommunityId(386);
		com.setCommunityDescription("World");
		com.setTotalMembers(120);
		com.setOnlineMembers(110);
		//To set File
		File fw = new File("abc.jpg");
		com.setImage(fw);
		
		//To set created date
		com.setCreatedOn(LocalDate.parse("2019-02-07"));
		
		//To set list of post rules allowed
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		com.setPostRulesAllowed(glist);
		
		//To set list of post rules disallowed
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		com.setPostRulesDisAllowed(galist);
		
		//To set list of Banning policy
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		com.setBanningPolicy(bp);
		
		//To set list of flairs
		List<String> f = new ArrayList<String>();
		f.add("DogsNews");
		com.setFlairs(f);
		
		//To set List of postIds &  Posts 
		
		//List of PostIds
		List<Integer> p = new ArrayList<Integer>();
		//List of posts
		List<Post> posts = new ArrayList<Post>();
				
		//Creating post object
		Post post1 = new Post();
		//Adding data to post object
		post1.setPostId(100);
		post1.setTitle("Documentry");
		post1.setContent(PostType.VIDEO_IMAGE);
		post1.setCreatedDateTime(LocalDateTime.now());
		post1.setFlair("UrbanLiving");
		post1.setNotSafeForWork(false);
		post1.setOriginalContent(true);
		post1.setVotes(10000);
		post1.setVoteUp(false);
		post1.setSpoiler(true);
				
		posts.add(post1);
		p.add(post1.getPostId());
		com.setPost(posts);
		
		//Update the community
		Community c = comServ.updateCommunityWithoutDto(com);
		
		//Validate details
		assertEquals(386,c.getCommunityId());
		assertEquals("World",c.getCommunityDescription());
		assertEquals(120,c.getTotalMembers());
		assertEquals(110,c.getOnlineMembers());
		assertEquals(fw,c.getImage());
		assertEquals(LocalDate.parse("2019-02-07"),c.getCreatedOn());
		assertEquals(glist,c.getPostRulesAllowed());
		assertEquals(galist,c.getPostRulesDisAllowed());
		assertEquals(bp,c.getBanningPolicy());
		assertEquals(f,c.getFlairs());
		assertEquals(1,c.getPost().size());
	}
	
	@Test
//	@Disabled
	void deleteCommunityTest()
	{	
		//Count before Delete operation
		long beforeDeletecount = comServ.count();
		
		//Delete the community
		Community c = comServ.deleteCommunity(382);
		
		//Count after delete operation
		long afterDeleteCount = comServ.count();
		
		//Validate
		assertEquals(beforeDeletecount,afterDeleteCount+1);
		
	}
	
	@Test
//	@Disabled
	void listAllCommunitiesTest()
	{
		List<Community> comList = comServ.listAllCommunities("Science");
		int noOfCommunities = comList.size();
		assertEquals(1,noOfCommunities);
	}
}
