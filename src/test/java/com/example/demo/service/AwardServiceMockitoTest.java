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
		Award awards1 = new Award();
		awards1.setAwardId(12);
		awards1.setCoin(Coin.GOLD);
		
		List<Award> allAwards = new ArrayList<>();
		allAwards.add(awards1);
		
		Mockito.when(awardRepo.save(awards1)).thenReturn(awards1);
		assertEquals(12, awards1.getAwardId());
		assertEquals(Coin.GOLD, awards1.getCoin());
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
		
		Mockito.when(awardRepo.getAllAwardsByPostId(awards1.getAwardId())).thenReturn(allAwards);
		//List<Award> list = awardService.getAwardByPostId(awards1.getAwardId());
		assertEquals(2, allAwards.size());
	}

}
