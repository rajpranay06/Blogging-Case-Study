package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Community;
import com.example.demo.exception.ComDescriptionNotFoundException;
import com.example.demo.exception.CommunityFoundException;
import com.example.demo.exception.CommunityNotFoundException;
import com.example.demo.repository.ICommunityRepository;


@Service
public class CommunityServiceImpl implements ICommunityService {
	
	@Autowired
	ICommunityRepository comRepo;

	@Override
	public Community addCommunity(Community community) {
		//Check whether community is already present in DB or not
		Optional<Community> opt = comRepo.findById(community.getCommunityId());
		if(opt.isPresent())
		{
			throw new CommunityFoundException("Community is already present with the given id: "+community.getCommunityId());
		}
		//Saving Community Object to Repository
		Community com = comRepo.save(community);
		return com;
	}

	@Override
	public Community updateCommunity(Community community) {
		//Check whether community is available in DB or not by using Id
		Optional<Community> opt = comRepo.findById(community.getCommunityId());
		if(!opt.isPresent())
		{
			throw new CommunityNotFoundException("Community not found with the given id:"+community.getCommunityId());
		}
		//Save Updated Community to repository
		return comRepo.save(community);
	}

	@Override
	public Community deleteCommunity(int comId) {
		//Check whether community is available in DB or not by using Id
		
		Optional<Community> opt = comRepo.findById(comId);
		if(!opt.isPresent())
		{
			throw new CommunityNotFoundException("Community not found with the given id:"+comId);
		}
		//Delete Community
		
		Community deletedCommunity = opt.get();
		comRepo.delete(deletedCommunity);
		return deletedCommunity;
	}

	@Override
	public List<Community> listAllCommunities(String searchString) {
		Optional<List<Community>> opt = comRepo.findByCommunityDescription(searchString);
		if(opt.isPresent() && opt.get().isEmpty())
		{
			throw new ComDescriptionNotFoundException("Community is not found with the given description : "+searchString);
		}
		
//		Get list of communities with same CommunityDescription
		List<Community> comList = comRepo.listAllCommunities(searchString);
		
		return comList;
	}

	@Override
	public long count() {
		return comRepo.count();
	}

	@Override
	public List<Community> listAllCommunitiesByBloggerId(int bloggerId) {
		
		List<Community> communities = comRepo.listAllCommunitiesByBloggerId(bloggerId);
		if(communities.isEmpty()) {
			throw new CommunityNotFoundException("No Community found with the given blogger id:"+ bloggerId);
		}
		return communities;
	}

}
