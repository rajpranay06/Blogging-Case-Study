package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.io.File;
import java.time.LocalDate;
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

import com.example.demo.bean.Community;
import com.example.demo.dto.CommunityDto;
import com.example.demo.repository.ICommunityRepository;

@ExtendWith(SpringExtension.class)
public class CommunityServiceMockitoTest {
	@InjectMocks
	CommunityServiceImpl comServ;
	
	@MockBean
	ICommunityRepository comRepo;
	
	@BeforeEach
	void init()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void addCommunityTest()
	{
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
		
		CommunityDto com = new CommunityDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f);
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
		
		
		Mockito.when(comRepo.save(newCommunity)).thenReturn(newCommunity);
		Community community = comServ.addCommunity(newCommunity);
		
		assertEquals(12,community.getCommunityId());
		assertEquals("Dogs",community.getCommunityDescription());
		assertEquals(400,community.getTotalMembers());
		assertEquals(fw,community.getImage());
		assertEquals(LocalDate.parse("2019-02-07"),community.getCreatedOn());
		assertEquals(glist,community.getPostRulesAllowed());
		assertEquals(galist,community.getPostRulesDisAllowed());
		assertEquals(bp,community.getBanningPolicy());
		assertEquals(f,community.getFlairs());
	}
	
	@Test
	void updateCommunityTest()
	{
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Adults");
		glist.add("Kids");
		glist.add("Teenage");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Buildings");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("Relationship");
		
		CommunityDto com = new CommunityDto(2,"Humans",430,230,fw,LocalDate.parse("2014-09-13"),glist,galist,bp,f);
		
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
		 
		Mockito.when(comRepo.findById(2)).thenReturn(Optional.of(newCommunity));
		Mockito.when(comRepo.save(newCommunity)).thenReturn(newCommunity);
		
		Community community = comServ.updateCommunity(newCommunity);
		
		
		
		assertEquals(2,community.getCommunityId());
		assertEquals("Humans",community.getCommunityDescription());
		assertEquals(430,community.getTotalMembers());
		assertEquals(fw,community.getImage());
		assertEquals(LocalDate.parse("2014-09-13"),community.getCreatedOn());
		assertEquals(glist,community.getPostRulesAllowed());
		assertEquals(galist,community.getPostRulesDisAllowed());
		assertEquals(bp,community.getBanningPolicy());
		assertEquals(f,community.getFlairs());
		
	}
	
	@Test
	void deleteCommunityTest()
	{
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Adults");
		glist.add("Kids");
		glist.add("Teenage");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Buildings");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("Relationship");
		
		CommunityDto community = new CommunityDto(30,"Humans",430,230,fw,LocalDate.parse("2014-09-13"),glist,galist,bp,f);
		Community newCommunity = new Community();
		newCommunity.setCommunityId(community.getCommunityId());
		newCommunity.setCommunityDescription(community.getCommunityDescription());
		newCommunity.setTotalMembers(community.getTotalMembers());
		newCommunity.setOnlineMembers(community.getOnlineMembers());
		newCommunity.setImage(community.getImage());
		newCommunity.setCreatedOn(community.getCreatedOn());
		newCommunity.setPostRulesAllowed(community.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(community.getBanningPolicy());
		newCommunity.setFlairs(community.getFlairs());
		
		Mockito.when(comRepo.findById(30)).thenReturn(Optional.of(newCommunity));
		
		doNothing().when(comRepo).deleteById(30);

		Community com = comServ.deleteCommunity(30);
		
		assertEquals(30,com.getCommunityId());
		assertEquals("Humans",com.getCommunityDescription());
		assertEquals(430,com.getTotalMembers());
		assertEquals(230, com.getOnlineMembers());
		assertEquals(fw,com.getImage());
		assertEquals(LocalDate.parse("2014-09-13"),com.getCreatedOn());
		assertEquals(glist,com.getPostRulesAllowed());
		assertEquals(galist,com.getPostRulesDisAllowed());
		assertEquals(bp,com.getBanningPolicy());
		assertEquals(f,com.getFlairs());		
		
	}
	
	@Test
	void listAllCommunitiesTest()
	{
		
		
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Adults");
		glist.add("Kids");
		glist.add("Teenage");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Buildings");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("Relationship");
		
		CommunityDto com1 = new CommunityDto(12,"Science",430,230,fw,LocalDate.parse("2014-09-13"),glist,galist,bp,f);
		
		CommunityDto com2 = new CommunityDto(13,"Humans",430,230,fw,LocalDate.parse("2014-09-13"),glist,galist,bp,f);
		
		Community newCommunity = new Community();
		newCommunity.setCommunityDescription(com1.getCommunityDescription());
		newCommunity.setTotalMembers(com1.getTotalMembers());
		newCommunity.setOnlineMembers(com1.getOnlineMembers());
		newCommunity.setImage(com1.getImage());
		newCommunity.setCreatedOn(com1.getCreatedOn());
		newCommunity.setPostRulesAllowed(com1.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(com1.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(com1.getBanningPolicy());
		newCommunity.setFlairs(com1.getFlairs());
		Mockito.when(comRepo.save(newCommunity)).thenReturn(newCommunity);
		
		Community newCommunity2 = new Community();
		newCommunity2.setCommunityDescription(com2.getCommunityDescription());
		newCommunity2.setTotalMembers(com2.getTotalMembers());
		newCommunity2.setOnlineMembers(com2.getOnlineMembers());
		newCommunity2.setImage(com2.getImage());
		newCommunity2.setCreatedOn(com2.getCreatedOn());
		newCommunity2.setPostRulesAllowed(com2.getPostRulesAllowed());
		newCommunity2.setPostRulesDisAllowed(com2.getPostRulesDisAllowed());
		newCommunity2.setBanningPolicy(com2.getBanningPolicy());
		newCommunity2.setFlairs(com2.getFlairs());
		
		List<Community> list = new ArrayList<>();
		
		List<Community> clist = new ArrayList<>();
		clist.add(newCommunity);
		clist.add(newCommunity2);
		
		for(Community c : clist)
		{
			if(c.getCommunityDescription().equals("Science"))
			{
				list.add(c);
			}
		}
		
		Mockito.when(comRepo.findByCommunityDescription("Science")).thenReturn(Optional.of(list));
		
		Mockito.when(comRepo.listAllCommunities("Science")).thenReturn(list);
		
		List<Community> comlist = comServ.listAllCommunities("Science");
		
		int noOfCommunities = comlist.size();
		
		assertEquals(1,noOfCommunities);
	}
	
	@Test
	void listAllCommunitiesByBloggerId() {
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Adults");
		glist.add("Kids");
		glist.add("Teenage");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Buildings");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("Relationship");
		
		CommunityDto com1 = new CommunityDto(12,"Science",430,230,fw,LocalDate.parse("2014-09-13"),glist,galist,bp,f);
		
		CommunityDto com2 = new CommunityDto(13,"Humans",430,230,fw,LocalDate.parse("2014-09-13"),glist,galist,bp,f);
		
		Community newCommunity = new Community();
		newCommunity.setCommunityDescription(com1.getCommunityDescription());
		newCommunity.setTotalMembers(com1.getTotalMembers());
		newCommunity.setOnlineMembers(com1.getOnlineMembers());
		newCommunity.setImage(com1.getImage());
		newCommunity.setCreatedOn(com1.getCreatedOn());
		newCommunity.setPostRulesAllowed(com1.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(com1.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(com1.getBanningPolicy());
		newCommunity.setFlairs(com1.getFlairs());
		Mockito.when(comRepo.save(newCommunity)).thenReturn(newCommunity);
		
		Community newCommunity2 = new Community();
		newCommunity2.setCommunityDescription(com2.getCommunityDescription());
		newCommunity2.setTotalMembers(com2.getTotalMembers());
		newCommunity2.setOnlineMembers(com2.getOnlineMembers());
		newCommunity2.setImage(com2.getImage());
		newCommunity2.setCreatedOn(com2.getCreatedOn());
		newCommunity2.setPostRulesAllowed(com2.getPostRulesAllowed());
		newCommunity2.setPostRulesDisAllowed(com2.getPostRulesDisAllowed());
		newCommunity2.setBanningPolicy(com2.getBanningPolicy());
		newCommunity2.setFlairs(com2.getFlairs());
		
		List<Community> clist = new ArrayList<>();
		clist.add(newCommunity);
		clist.add(newCommunity2);
		
		Mockito.when(comRepo.listAllCommunitiesByBloggerId(20)).thenReturn(clist);
		
		List<Community> communities = comServ.listAllCommunitiesByBloggerId(20);
		
		assertEquals(2, communities.size());
		
	}

}
