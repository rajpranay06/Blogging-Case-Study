package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Award;
import com.example.demo.exception.AwardNotFoundException;
import com.example.demo.repository.IAwardRepository;

@Service
public class AwardServiceImpl implements IAwardService{
	
	@Autowired
	IAwardRepository awardRepo;
	
	@Override
	public List<Award> viewAllAwards() {
		return awardRepo.findAll();
	}

	@Override
	public Award addAward(Award award) {
		//save awards in DB
		return awardRepo.save(award);
	}

	@Override
	public void deleteAwardById(int awardId) {
		Optional<Award> opt = awardRepo.findById(awardId);
		if(!opt.isPresent()) {
			throw new AwardNotFoundException("No award found with id: " + awardId);
		}
		awardRepo.delete(opt.get());
		
	}

	@Override
	public List<Award> getAwardByPostId(int id) {
		List<Award> awards = awardRepo.getAllAwardsByPostId(id);
		if(awards.isEmpty()) {
			throw new AwardNotFoundException("award not found with given Blog id");
		}
		return  awards;
	}
	
	@Override
	public List<Award> getAwardsByBlogId(int id) {
		List<Award> awards = awardRepo.getAwardsByBlogId(id);
		if(awards.isEmpty()) {
			throw new AwardNotFoundException("award not found with given Blog id");
		}
		return  awards;
	}

	

}
