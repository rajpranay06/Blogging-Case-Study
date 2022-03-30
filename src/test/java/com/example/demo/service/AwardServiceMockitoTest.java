package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
import com.example.demo.repository.IAwardRepository;

@ExtendWith(SpringExtension.class)
class AwardServiceMockitoTest {

	@InjectMocks
	AwardServiceImpl awardService;
	
	@MockBean
	IAwardRepository awardRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void addAwardTest() {
		Award awards = new Award();
		awards.setAwardId(12);
		awards.setCoin(Coin.GOLD);
		
		Mockito.when(awardRepo.save(awards)).thenReturn(awards);
		
		Award award = awardService.addAward(awards);
		
		assertEquals(12, award.getAwardId());
		assertEquals(Coin.GOLD, award.getCoin());
	}
	
	@Test
	void getAwardByPostIdTest() {
		Award awards1 = new Award();
		awards1.setAwardId(12);
		awards1.setCoin(Coin.GOLD);
	    
		Award awards2 = new Award();
		awards2.setAwardId(13);
		awards2.setCoin(Coin.PLATINUM);
		
		List<Award> allAwards = new ArrayList<>();
		allAwards.add(awards1);
		allAwards.add(awards2);
		
		Mockito.when(awardRepo.getAllAwardsByPostId(100)).thenReturn(allAwards);
		
		List<Award> list = awardService.getAwardByPostId(100);
		
		assertEquals(2, list.size());
	}

}
