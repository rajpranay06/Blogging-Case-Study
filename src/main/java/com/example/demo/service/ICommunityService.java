package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Community;


public interface ICommunityService {
	public Community addCommunity(Community community);
	public Community updateCommunity(Community community);
	public Community deleteCommunity(int comId);
	public List<Community> listAllCommunities(String searchString);
//	public List<Community> listAllCommunitiesByBlogger(Blogger blogger);
	public long count();
	
}
