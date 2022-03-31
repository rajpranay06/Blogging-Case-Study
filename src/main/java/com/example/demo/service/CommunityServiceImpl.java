package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Community;
import com.example.demo.dto.CommunityInputDto;
import com.example.demo.dto.CommunityOutputDto;
import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.exception.CommunityNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommunityRepository;
import com.example.demo.repository.IPostRepository;

@Service
public class CommunityServiceImpl implements ICommunityService {

	
	@Autowired
	ICommunityRepository comRepo;

	@Autowired
	IPostRepository postRepo;

	@Autowired
	IBloggerRepository bloggerRepo;

	@Override
	public Community addCommunity(CommunityInputDto community) {
		// Check whether community is already present in DB or not
		Optional<Community> opt = comRepo.findById(community.getCommunityId());

		if (opt.isPresent()) {
			throw new CommunityNotFoundException("Community is not found with the given id: " + community.getCommunityId());
		}
		
		//Create community object
		Community newCommunity = new Community();
		
		//Set values to Community object
		newCommunity.setCommunityDescription(community.getCommunityDescription());
		newCommunity.setTotalMembers(community.getTotalMembers());
		newCommunity.setOnlineMembers(community.getOnlineMembers());
		newCommunity.setImage(community.getImage());
		newCommunity.setCreatedOn(community.getCreatedOn());
		newCommunity.setPostRulesAllowed(community.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(community.getBanningPolicy());
		newCommunity.setFlairs(community.getFlairs());
		
		// Saving Community Object to Repository
		return comRepo.save(newCommunity);
	}

	@Override
	public Community updateCommunity(CommunityInputDto community) {
		//Find community by Id
		Optional<Community> opt = comRepo.findById(community.getCommunityId());
		if (!opt.isPresent()) {
			throw new CommunityNotFoundException("Community not found with the given id:" + community.getCommunityId());
		}
		Community newCommunity = opt.get();
		newCommunity.setCommunityDescription(community.getCommunityDescription());
		newCommunity.setTotalMembers(community.getTotalMembers());
		newCommunity.setOnlineMembers(community.getOnlineMembers());
		newCommunity.setImage(community.getImage());
		newCommunity.setCreatedOn(community.getCreatedOn());
		newCommunity.setPostRulesAllowed(community.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(community.getBanningPolicy());
		newCommunity.setFlairs(community.getFlairs());

		// Save Updated Community to repository
		return comRepo.save(newCommunity);

	}

	@Override
	public void deleteCommunity(int comId) {
		// Check whether community is available in DB or not by using Id

		Optional<Community> opt = comRepo.findById(comId);
		if (!opt.isPresent()) {
			throw new CommunityNotFoundException("Community not found with the given id:" + comId);
		}
		
		// Delete Community
		Community deletedCommunity = opt.get();
		comRepo.delete(deletedCommunity);
	}

	@Override
	public List<CommunityOutputDto> listAllCommunities() {
		
		List<Community> comList= comRepo.findAll();
		
		if(comList.isEmpty()) {
			throw new CommunityNotFoundException("No community is found");
		}
		
		// List to store community output dtos
		List<CommunityOutputDto> communities = new ArrayList<>();
		
		for(Community community : comList) {
			
			//Creating communityOutputDto object
			CommunityOutputDto com = new CommunityOutputDto();
			
			//Set values to community object
			com.setCommunityId(community.getCommunityId());
			com.setCommunityDescription(community.getCommunityDescription());
			com.setTotalMembers(community.getTotalMembers());
			com.setOnlineMembers(community.getOnlineMembers());
			com.setImage(community.getImage());
			com.setCreatedOn(community.getCreatedOn());
			com.setPostRulesAllowed(community.getPostRulesAllowed());
			com.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
			com.setBanningPolicy(community.getBanningPolicy());
			com.setFlairs(community.getFlairs());
			
			// Adding com to communities
			communities.add(com);
		}
		
		return communities;
	}
	
	@Override
	public CommunityOutputDto getCommunityByPostId(int postId) {
		//Check whether community is present with the given postId
		Community community = comRepo.getCommunityByPostId(postId);
		if(community==null)
		{
			throw new CommentNotFoundException("No community found with postID : "+postId);
		}
		//Creating communityOutputDto object
		CommunityOutputDto com = new CommunityOutputDto();
		
		//Set values to community object
		com.setCommunityId(community.getCommunityId());
		com.setCommunityDescription(community.getCommunityDescription());
		com.setTotalMembers(community.getTotalMembers());
		com.setOnlineMembers(community.getOnlineMembers());
		com.setImage(community.getImage());
		com.setCreatedOn(community.getCreatedOn());
		com.setPostRulesAllowed(community.getPostRulesAllowed());
		com.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
		com.setBanningPolicy(community.getBanningPolicy());
		com.setFlairs(community.getFlairs());
		
		return com;
	}

	@Override
	public long count() {
		return comRepo.count();
	}

	@Override

	public Community addCommunityWithoutDto(Community community) {
		return comRepo.save(community);
	}

	@Override
	public Community updateCommunityWithoutDto(Community community) {
		Optional<Community> opt = comRepo.findById(community.getCommunityId());
		if (!opt.isPresent()) {
			throw new CommunityNotFoundException("Community not found with the given id:" + community.getCommunityId());
		}
		// Save Updated Community to repository
		return comRepo.save(community);
	}

	

	public List<CommunityOutputDto> listAllCommunitiesByBloggerId(int bloggerId) {
		
		List<Community> comList = comRepo.listAllCommunitiesByBloggerId(bloggerId);
		if(comList.isEmpty()) {
			throw new CommunityNotFoundException("No community is found with blogger id: " + bloggerId);
		}
		
		// List to store community output dtos
		List<CommunityOutputDto> communities = new ArrayList<>();
		
		for(Community community : comList) {
			
			//Creating communityOutputDto object
			CommunityOutputDto com = new CommunityOutputDto();
			
			//Set values to community object
			com.setCommunityId(community.getCommunityId());
			com.setCommunityDescription(community.getCommunityDescription());
			com.setTotalMembers(community.getTotalMembers());
			com.setOnlineMembers(community.getOnlineMembers());
			com.setImage(community.getImage());
			com.setCreatedOn(community.getCreatedOn());
			com.setPostRulesAllowed(community.getPostRulesAllowed());
			com.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
			com.setBanningPolicy(community.getBanningPolicy());
			com.setFlairs(community.getFlairs());
			
			// Adding com to communities
			communities.add(com);
		}
		
		return communities;
	}

	@Override
	public List<CommunityOutputDto> listAllCommunitiesByDescription(String communityDescription) {
		String description = '%' + communityDescription + '%';
		// Creating a list of PostOutputDto
		List<Community> allCommunities = comRepo.listAllCommunitiesByDescription(description);
				
		if(allCommunities.isEmpty()) {
			throw new CommunityNotFoundException("No community with deascription: " + communityDescription);
		}
		
		// List to store community output dtos
		List<CommunityOutputDto> communities = new ArrayList<>();
		
		for(Community community : allCommunities) {
			
			//Creating communityOutputDto object
			CommunityOutputDto com = new CommunityOutputDto();
			
			//Set values to community object
			com.setCommunityId(community.getCommunityId());
			com.setCommunityDescription(community.getCommunityDescription());
			com.setTotalMembers(community.getTotalMembers());
			com.setOnlineMembers(community.getOnlineMembers());
			com.setImage(community.getImage());
			com.setCreatedOn(community.getCreatedOn());
			com.setPostRulesAllowed(community.getPostRulesAllowed());
			com.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
			com.setBanningPolicy(community.getBanningPolicy());
			com.setFlairs(community.getFlairs());
			
			// Adding com to communities
			communities.add(com);
		}
		
		return communities;
	}

}
