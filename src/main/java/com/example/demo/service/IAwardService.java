package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Award;

public interface IAwardService {

	Award addAward(Award award);
	Award deleteAwardById(int awardId);
	List<Award> getAwardByPostId(int id);
}
