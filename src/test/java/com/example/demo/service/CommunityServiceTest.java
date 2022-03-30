package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.Community;
import com.example.demo.dto.CommunityOutputDto;


@SpringBootTest
public class CommunityServiceTest {
	@Autowired
	ICommunityService comServ;
	
	@Test
	@Disabled
	void addCommunityTest()
	{
		//Create community object
		Community com = new Community();
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
	}
	
	@Test
	@Disabled
	void updateCommunityTest()
	{
		Community com = new Community();
		com.setCommunityId(11);
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
		
		//Update the community
		Community c = comServ.updateCommunityWithoutDto(com);
		
		//Validate details
		assertEquals(11,c.getCommunityId());
		assertEquals("World",c.getCommunityDescription());
		assertEquals(120,c.getTotalMembers());
		assertEquals(110,c.getOnlineMembers());
		assertEquals(fw,c.getImage());
		assertEquals(LocalDate.parse("2019-02-07"),c.getCreatedOn());
		assertEquals(glist,c.getPostRulesAllowed());
		assertEquals(galist,c.getPostRulesDisAllowed());
		assertEquals(bp,c.getBanningPolicy());
		assertEquals(f,c.getFlairs());
	}
	
	@Test
	void listAllCommunitiesTest()
	{
		List<CommunityOutputDto> comList = comServ.listAllCommunitiesByDescription("Test");
		int noOfCommunities = comList.size();
		assertEquals(4,noOfCommunities);
	}
	
	@Test
	void getCommunityByPostIdTest()
	{	
		CommunityOutputDto com = comServ.getCommunityByPostId(16);
		
		assertEquals(13,com.getCommunityId());
		assertEquals("Community Test 4",com.getCommunityDescription());
		assertEquals(100,com.getTotalMembers());
		
	}
	
	@Test
	void getCommunityByBloggerIdTest()
	{	
		List<CommunityOutputDto> com = comServ.listAllCommunitiesByBloggerId(15);
		
		assertEquals(2,com.size());
		
	}
}
