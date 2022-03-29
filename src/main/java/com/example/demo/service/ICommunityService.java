package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Community;
import com.example.demo.bean.Post;
import com.example.demo.dto.CommunityInputDto;


public interface ICommunityService {
	public Community addCommunity(CommunityInputDto community);
	public Community updateCommunity(CommunityInputDto community);
	public Community deleteCommunity(int comId);
	public List<Community> listAllCommunities(String searchString);

	public List<Community> listAllCommunitiesByBloggerId(int bloggerId);

	public long count();
	public Community addCommunityWithoutDto(Community community);
	public Community updateCommunityWithoutDto(Community community);
	public Community getCommunityByPostId(int postId);
	
	
	
}
