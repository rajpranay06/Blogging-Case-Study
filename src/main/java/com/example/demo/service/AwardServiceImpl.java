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
	public Award addAward(Award award) {
		//save awards in DB
		return awardRepo.save(award);
	}

	@Override
	public Award deleteAwardById(int awardId) {
		Award aw = awardRepo.getById(awardId);
		if(aw != null) {
			awardRepo.deleteById(awardId);
			return aw;
		}
		return null;
	}

	@Override
	public List<Award> getAwardByPostId(int id) {
		List<Award> awards = awardRepo.getAllAwardsByPostId(id);
		if(awards.isEmpty()) {
			throw new AwardNotFoundException("award not found with given post id");
		}
		//return awardRepo.getAllAwardsByPostId(id);
		return  awards;
	}

	

}
