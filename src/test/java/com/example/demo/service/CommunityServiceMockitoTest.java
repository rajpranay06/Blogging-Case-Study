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
import com.example.demo.dto.CommunityInputDto;
import com.example.demo.dto.CommunityOutputDto;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommunityRepository;
import com.example.demo.repository.IPostRepository;


@ExtendWith(SpringExtension.class)
public class CommunityServiceMockitoTest {
	@InjectMocks
	CommunityServiceImpl comServ;
	
	@MockBean
	ICommunityRepository comRepo;
	
	@MockBean
	IPostRepository postRepo;
	
	@MockBean
	IBloggerRepository blogRepo;
	
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
		
		List<Award> awards = new ArrayList<>();
		Award award = new Award();
		award.setAwardId(5);
		award.setCoin(Coin.GOLD);
		
		awards.add(award);
		
		post1.setAwards(awards);
	
		posts.add(post1);
		
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
		
		Mockito.when(comRepo.save(newCommunity)).thenReturn(newCommunity);
		
		Community community = comServ.addCommunityWithoutDto(newCommunity);
		
		assertEquals(12,community.getCommunityId());
		assertEquals("Dogs",community.getCommunityDescription());
		assertEquals(400,community.getTotalMembers());
		assertEquals(fw,community.getImage());
		assertEquals(LocalDate.parse("2019-02-07"),community.getCreatedOn());
		assertEquals(glist,community.getPostRulesAllowed());
		assertEquals(galist,community.getPostRulesDisAllowed());
		assertEquals(bp,community.getBanningPolicy());
		assertEquals(f,community.getFlairs());
		assertEquals(1,community.getPost().size());
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
		
		List<Award> awards = new ArrayList<>();
		Award award = new Award();
		award.setAwardId(5);
		award.setCoin(Coin.GOLD);
		
		awards.add(award);
		
		post1.setAwards(awards);
		
		Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post1));
		posts.add(post1);
		p.add(post1.getPostId());
		
		CommunityInputDto com = new CommunityInputDto(2,"Humans",430,230,fw,LocalDate.parse("2014-09-13"),glist,galist,bp,f,p);
		
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
		 
		Mockito.when(comRepo.findById(2)).thenReturn(Optional.of(newCommunity));
		Mockito.when(comRepo.save(newCommunity)).thenReturn(newCommunity);
		
		Community community = comServ.updateCommunityWithoutDto(newCommunity);
		
		
		
		assertEquals(2,community.getCommunityId());
		assertEquals("Humans",community.getCommunityDescription());
		assertEquals(430,community.getTotalMembers());
		assertEquals(fw,community.getImage());
		assertEquals(LocalDate.parse("2014-09-13"),community.getCreatedOn());
		assertEquals(glist,community.getPostRulesAllowed());
		assertEquals(galist,community.getPostRulesDisAllowed());
		assertEquals(bp,community.getBanningPolicy());
		assertEquals(f,community.getFlairs());
		assertEquals(1,community.getPost().size());
	}
	
	
	@Test
	void getCommunityByPostId()
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
		
		CommunityInputDto com1 = new CommunityInputDto(12,"Science",430,230,fw,LocalDate.parse("2014-09-13"),glist,galist,bp,f,p);
		
		Community newCommunity = new Community();
		newCommunity.setCommunityId(com1.getCommunityId());
		newCommunity.setCommunityDescription(com1.getCommunityDescription());
		 newCommunity.setTotalMembers(com1.getTotalMembers());
		 newCommunity.setOnlineMembers(com1.getOnlineMembers());
		 newCommunity.setImage(com1.getImage());
		 newCommunity.setCreatedOn(com1.getCreatedOn());
		 newCommunity.setPostRulesAllowed(com1.getPostRulesAllowed());
		 newCommunity.setPostRulesDisAllowed(com1.getPostRulesDisAllowed());
		 newCommunity.setBanningPolicy(com1.getBanningPolicy());
		 newCommunity.setFlairs(com1.getFlairs());
		 newCommunity.setPost(posts);
		 
		Mockito.when(comRepo.save(newCommunity)).thenReturn(newCommunity);
		comServ.addCommunity(com1);
		
		Mockito.when(comRepo.getCommunityByPostId(100)).thenReturn(newCommunity);
		CommunityOutputDto community = comServ.getCommunityByPostId(100);
		
		assertEquals(12,community.getCommunityId());
		assertEquals("Science",community.getCommunityDescription());
		assertEquals(430,community.getTotalMembers());
		assertEquals(230,community.getOnlineMembers());
		assertEquals(fw,community.getImage());
		assertEquals(LocalDate.parse("2014-09-13"),community.getCreatedOn());
		assertEquals(glist,community.getPostRulesAllowed());
		assertEquals(galist,community.getPostRulesDisAllowed());
		assertEquals(bp,community.getBanningPolicy());
		assertEquals(f,community.getFlairs());
		
	}
}
