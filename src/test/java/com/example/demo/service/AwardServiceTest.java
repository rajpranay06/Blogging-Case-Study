package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

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

		Award awards = new Award();
		awards.setAwardId(107);
		awards.setAwardId(1);

		Award award = awardServ.addAward(awards);
		assertEquals(107, award.getAwardId());

		Award awards1 = new Award();
		awards1.setCoin(Coin.GOLD);
		
		Award award = awardServ.addAward(awards1);
		assertEquals(Coin.GOLD, award.getCoin());

	}

	@Test
	void getAwardByPostId() {
		
		List<Award> awards = awardServ.getAwardByPostId(10);
		
		assertEquals(2, awards.size());
	}

	@Test
	void getAwardsByBlogId() {
		List<Award> awards = awardServ.getAwardsByBlogId(63);
		assertEquals(1, awards.size());
	}
}
