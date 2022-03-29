package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.Award;
import com.example.demo.bean.Comment;


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
	}
	
	@Test
	void getAwardByPostId() {
		List<Award> awards = awardServ.getAwardByPostId(94);
		assertEquals(1, awards.size());
	}

}
