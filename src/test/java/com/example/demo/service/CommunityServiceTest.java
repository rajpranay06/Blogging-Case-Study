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
import com.example.demo.dto.CommunityDto;

@SpringBootTest
public class CommunityServiceTest {
	
	@Autowired
	ICommunityService comServ;
	
	@Test
	@Disabled
	void addCommunityTest()
	{
		//Create community object
		CommunityDto com = new CommunityDto();
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
		
		Community community = new Community();
		community.setCommunityDescription(com.getCommunityDescription());
		community.setBanningPolicy(com.getBanningPolicy());
		community.setCreatedOn(com.getCreatedOn());
		community.setImage(com.getImage());
		community.setFlairs(com.getFlairs());
		community.setOnlineMembers(com.getOnlineMembers());
		community.setPostRulesAllowed(com.getPostRulesAllowed());
		community.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
		community.setTotalMembers(com.getTotalMembers());
		
		//Persist the community object to the DB using service implementation
		Community addedCommunity = comServ.addCommunity(community);
		
		//Validate details
		assertEquals("Humans",addedCommunity.getCommunityDescription());
		assertEquals(120,addedCommunity.getTotalMembers());
		assertEquals(110,addedCommunity.getOnlineMembers());
		assertEquals(fw,addedCommunity.getImage());
		assertEquals(LocalDate.parse("2019-02-07"),addedCommunity.getCreatedOn());
		assertEquals(glist,addedCommunity.getPostRulesAllowed());
		assertEquals(galist,addedCommunity.getPostRulesDisAllowed());
		assertEquals(bp,addedCommunity.getBanningPolicy());
		assertEquals(f,addedCommunity.getFlairs());
	}
	
	@Test
	@Disabled
	void updateCommunityTest()
	{
		//Create community object
		CommunityDto com = new CommunityDto();
		com.setCommunityId(70);
		com.setCommunityDescription("World");
		com.setTotalMembers(120);
		com.setOnlineMembers(110);
		//To set File
		File fw = new File("abc.jpg");
		com.setImage(fw);
		
		//To set created date
		com.setCreatedOn(LocalDate.parse("2022-03-28"));
		
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
		
		Community community = new Community();
		community.setCommunityId(com.getCommunityId());
		community.setCommunityDescription(com.getCommunityDescription());
		community.setBanningPolicy(com.getBanningPolicy());
		community.setCreatedOn(com.getCreatedOn());
		community.setImage(com.getImage());
		community.setFlairs(com.getFlairs());
		community.setOnlineMembers(com.getOnlineMembers());
		community.setPostRulesAllowed(com.getPostRulesAllowed());
		community.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
		community.setTotalMembers(com.getTotalMembers());
		
		//Update the community
		Community c = comServ.updateCommunity(community);
		
		//Validate details
		assertEquals(70,c.getCommunityId());
		assertEquals("World",c.getCommunityDescription());
		assertEquals(120,c.getTotalMembers());
		assertEquals(110,c.getOnlineMembers());
		assertEquals(fw,c.getImage());
		assertEquals(LocalDate.parse("2022-03-28"),c.getCreatedOn());
		assertEquals(glist,c.getPostRulesAllowed());
		assertEquals(galist,c.getPostRulesDisAllowed());
		assertEquals(bp,c.getBanningPolicy());
		assertEquals(f,c.getFlairs());
	}
	
	@Test
	@Disabled
	void deleteCommunityTest()
	{	
		//Count before Delete operation
		long beforeDeletecount = comServ.count();
		
		//Delete the community
		comServ.deleteCommunity(70);
		
		//Count after delete operation
		long afterDeleteCount = comServ.count();
		
		//Validate
		assertEquals(beforeDeletecount,afterDeleteCount+1);
		
	}
	
	@Test
	@Disabled
	void listAllCommunitiesTest()
	{
		List<Community> comList = comServ.listAllCommunities("Science");
		int noOfCommunities = comList.size();
		assertEquals(1,noOfCommunities);
	}
	
	@Test
	void listAllCommunitiesByBloggerId() {
		
		List<Community> communities = comServ.listAllCommunitiesByBloggerId(63);
		
		assertEquals(2, communities.size());
	}
}
