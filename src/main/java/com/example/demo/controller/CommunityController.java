package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Community;
import com.example.demo.dto.CommunityDto;
import com.example.demo.service.ICommunityService;




@RestController
public class CommunityController {
	@Autowired
	ICommunityService comServ;
	
	@PostMapping("/communities")
	ResponseEntity<Community> addCommunity(@RequestBody CommunityDto com)
	{
		Community com1 = new Community();
		com1.setCommunityId(com.getCommunityId());
		com1.setCommunityDescription(com.getCommunityDescription());
		com1.setTotalMembers(com.getTotalMembers());
		com1.setOnlineMembers(com.getOnlineMembers());
		com1.setImage(com.getImage());
		com1.setCreatedOn(com.getCreatedOn());
		com1.setPostRulesAllowed(com.getPostRulesAllowed());
		com1.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
		com1.setBanningPolicy(com.getBanningPolicy());
		com1.setFlairs(com.getFlairs());
		Community newCom = comServ.addCommunity(com1);
		return new ResponseEntity<>(newCom,HttpStatus.CREATED);
	}
	
	@PutMapping("/communities")
	ResponseEntity<Community> updateCommunity(@RequestBody CommunityDto com)
	{
		Community com1 = new Community();
		com1.setCommunityId(com.getCommunityId());
		com1.setCommunityDescription(com.getCommunityDescription());
		com1.setTotalMembers(com.getTotalMembers());
		com1.setOnlineMembers(com.getOnlineMembers());
		com1.setImage(com.getImage());
		com1.setCreatedOn(com.getCreatedOn());
		com1.setPostRulesAllowed(com.getPostRulesAllowed());
		com1.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
		com1.setBanningPolicy(com.getBanningPolicy());
		com1.setFlairs(com.getFlairs());
		Community upCom = comServ.updateCommunity(com1);
		return new ResponseEntity<>(upCom,HttpStatus.OK);
	}
	
	@DeleteMapping("/communities/ByCommunityId/{comId}")
	ResponseEntity<Community> deleteCommunity(@PathVariable("comId") int comId)
	{
		Community delCom = comServ.deleteCommunity(comId);
		return new ResponseEntity<>(delCom,HttpStatus.OK);
	}
	
	@GetMapping("/communities/{communityDescription}")
	ResponseEntity<List<Community>> listAllCommunities(@PathVariable("communityDescription") String communityDescription)
	{
		List<Community> listCom = comServ.listAllCommunities(communityDescription);
		return new ResponseEntity<>(listCom,HttpStatus.OK);
	}
	
}
