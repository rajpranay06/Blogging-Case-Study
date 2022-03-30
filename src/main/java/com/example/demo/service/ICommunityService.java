package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Community;
import com.example.demo.dto.CommunityInputDto;
import com.example.demo.dto.CommunityOutputDto;


public interface ICommunityService {
	
	public Community addCommunity(CommunityInputDto community);
	public Community updateCommunity(CommunityInputDto community);
	public void deleteCommunity(int comId);
	public List<CommunityOutputDto> listAllCommunities();
	public List<CommunityOutputDto> listAllCommunitiesByBloggerId(int bloggerId);
	public long count();
	public Community addCommunityWithoutDto(Community community);
	public Community updateCommunityWithoutDto(Community community);
	public CommunityOutputDto getCommunityByPostId(int postId);
	public List<CommunityOutputDto> listAllCommunitiesByDescription(String communityDescription);
	
}