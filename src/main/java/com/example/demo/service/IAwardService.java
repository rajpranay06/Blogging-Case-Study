package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Award;

public interface IAwardService {

	public Award addAward(Award award);
	public void deleteAwardById(int awardId);
	public List<Award> getAwardByPostId(int id);
	public List<Award> getAwardsByBlogId(int id);
}
