package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.Award;
import com.example.demo.bean.Coin;


@SpringBootTest
class AwardServiceTest {
	
	@Autowired
	IAwardService awardServ;

	@Test
	@Disabled
	void addAwardTest() {
		Award awards1 = new Award();
		awards1.setAwardId(12);
		awards1.setCoin(Coin.GOLD);
		
		Award award = awardServ.addAward(awards1);
		assertEquals(12, awards1.getAwardId());
		assertEquals(Coin.GOLD, awards1.getCoin());
	}
	
	@Test
	void getAwardByPostId() {
		Award awards1 = new Award();
		awards1.setAwardId(121);
		awards1.setCoin(Coin.GOLD);
		
		List<Award> allAwards = new ArrayList<>();
		allAwards.add(awards1);
		
		List<Award> awards = awardServ.getAwardByPostId(awards1.getAwardId());
		assertEquals(2, awards.size());
		//List<Award> awards = awardServ.getAwardByPostId(94);
		//assertEquals(1, awards.size());
	}

}
