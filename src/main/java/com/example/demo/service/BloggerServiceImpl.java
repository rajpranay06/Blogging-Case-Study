package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BloggerDto;
import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.dto.CommunityOutputDto;
import com.example.demo.dto.UserOutputDto;
import com.example.demo.bean.Award;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Community;
import com.example.demo.bean.UserEntity;
import com.example.demo.exception.BloggerIdNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.IAwardRepository;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.exception.AwardNotFoundException;
import com.example.demo.exception.CommunityNotFoundException;
import com.example.demo.repository.ICommunityRepository;
import com.example.demo.repository.IUserRepository;

@Service
public class BloggerServiceImpl implements IBloggerService {

	@Autowired
	IBloggerRepository blogRepo;
	
	@Autowired
	ICommunityRepository commRepo;
	
	@Autowired
	IAwardRepository awardRepo;
	
	@Autowired
	IUserRepository userRepo;

	@Override
	public Blogger addBlogger(Blogger blogger) {
		return blogRepo.save(blogger);
	}

	@Override
	public BloggerDto addBloggerDto(BloggerInputDto bloggerInputDto) {
		
		// Creating blogger object
		Blogger blog = new Blogger();
		
		//Setting blogger variables by bloggerInputDto values
		blog.setBloggerName(bloggerInputDto.getBloggerName());
			
		// List to store communities
		List<Community> communities = new ArrayList<>();
		
		// Getting community IDs
		List<Integer> communityIds = bloggerInputDto.getCommunityIds();
		if(!communityIds.isEmpty()) {
			for(Integer id : communityIds) {
				
				// Getting community by ID
				Optional<Community> opt = commRepo.findById(id);
				if(!opt.isPresent()) {
					throw new CommunityNotFoundException("No community is for with given id: "+ id);				}
				
				// Adding community to list
				communities.add(opt.get());
			}
			// Setting the communities to blog
			blog.setCommunities(communities);
		}
		
		// List to store awards
		List<Award> awards = new ArrayList<>();

		// Getting award IDs
		List<Integer> awardIds = bloggerInputDto.getAwardsIds();
		if (!awardIds.isEmpty()) {
			for (Integer id : awardIds) {

				// Getting award by ID
				Optional<Award> opt = awardRepo.findById(id);
				if (!opt.isPresent()) {
					throw new AwardNotFoundException("Award is not found with the given id: " + id);
				}

				// Adding award to list
				awards.add(opt.get());
			}
			// Setting the awards to blog
			blog.setAwards(awards);
		}
		
		// Getting the user by ID
		Optional<UserEntity> user = userRepo.findById(bloggerInputDto.getUserId());
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("No user found with id: " + bloggerInputDto.getUserId());
		}
		
		// Setting the user to blogger
		blog.setUser(user.get());
		
		// Saving the blogger in database
		Blogger newBlogger = blogRepo.save(blog);	
		
		BloggerDto bloggerDto = new BloggerDto();
		bloggerDto.setBloggerId(newBlogger.getBloggerId());
		bloggerDto.setBloggerName(newBlogger.getBloggerName());
		bloggerDto.setKarma(50);
		
		List<CommunityOutputDto> newCommunities = new ArrayList<>();
		
		for(Community community : newBlogger.getCommunities()) {
			
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
			newCommunities.add(com);
		}
		
		UserEntity blogUser = newBlogger.getUser();
		// Creating a userOutputDto
		UserOutputDto userOutput = new UserOutputDto();
		userOutput.setUserId(blogUser.getUserId());
		userOutput.setEmail(blogUser.getEmail());
		userOutput.setLoginStatus(blogUser.isLoginStatus());
		userOutput.setRole(blogUser.getRole());
		
		bloggerDto.setCommunities(newCommunities);
		bloggerDto.setAwards(newBlogger.getAwards());
		bloggerDto.setUser(userOutput);

		return bloggerDto;
		
	}

	@Override
	public BloggerDto updateBlogger(BloggerInputDto blogger) {
		
		Optional<Blogger> opt1 = blogRepo.findById(blogger.getBloggerId());
		if (!opt1.isPresent()) {
			throw new BloggerIdNotFoundException("Blogger not found with the given id:" + blogger.getUserId());
		}
		Blogger updateBlogger = opt1.get();
		
		// Setting values to updateBlogger
		updateBlogger.setBloggerName(blogger.getBloggerName());
		

		// List to store communities
		List<Community> communities = new ArrayList<>();
		
		List<Integer> communityIds = blogger.getCommunityIds();
		if(!communityIds.isEmpty()) {
			for(Integer id : communityIds) {
				Optional<Community> opt = commRepo.findById(id);
				if(opt.isPresent()) {
					communities.add(opt.get());
				}
				else {
					throw new CommunityNotFoundException("No community is for with given id: "+ id);
				}
			}
			updateBlogger.setCommunities(communities);
		}
		
		// List to store awards
		List<Award> awards = new ArrayList<>();

		List<Integer> awardIds = blogger.getAwardsIds();
		if (!awardIds.isEmpty()) {
			for (Integer id : awardIds) {
				Optional<Award> award = awardRepo.findById(id);
				if (!award.isPresent()) {
					throw new AwardNotFoundException("Award not found with the given id: " + id);
				}

				awards.add(award.get());
			}
		}
		updateBlogger.setAwards(awards);
		
		// Getting the user by ID
		Optional<UserEntity> user = userRepo.findById(blogger.getUserId());
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("No user found with id: " + blogger.getUserId());
		}
		
		// Setting the user to blogger
		updateBlogger.setUser(user.get());
		
		Blogger newBlogger = blogRepo.save(updateBlogger);
		
		BloggerDto bloggerDto = new BloggerDto();
		bloggerDto.setBloggerId(newBlogger.getBloggerId());
		bloggerDto.setBloggerName(newBlogger.getBloggerName());
		bloggerDto.setKarma(newBlogger.getKarma());
		
		List<CommunityOutputDto> newCommunities = new ArrayList<>();
		
		for(Community community : newBlogger.getCommunities()) {
			
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
			newCommunities.add(com);
		}
		
		UserEntity blogUser = newBlogger.getUser();
		// Creating a userOutputDto
		UserOutputDto userOutput = new UserOutputDto();
		userOutput.setUserId(blogUser.getUserId());
		userOutput.setEmail(blogUser.getEmail());
		userOutput.setLoginStatus(blogUser.isLoginStatus());
		userOutput.setRole(blogUser.getRole());
		
		bloggerDto.setCommunities(newCommunities);
		bloggerDto.setAwards(newBlogger.getAwards());
		bloggerDto.setUser(userOutput);
		
		return bloggerDto;

	}

	@Override
	public void deleteBlogger(int bloggerId) {
		
		Optional<Blogger> opt = blogRepo.findById(bloggerId);
		if (!opt.isPresent()) {
			throw new BloggerIdNotFoundException("Blogger not found with the given id:" + bloggerId);
		}
		
		Blogger blogger = opt.get();
		blogRepo.delete(blogger);
	}

	@Override
	public BloggerOutputDto viewBlogger(int bloggerId) {

		Optional<Blogger> opt = blogRepo.findById(bloggerId);
		if (!opt.isPresent()) {
			throw new BloggerIdNotFoundException("Blogger not found with the given id:" + bloggerId);
		}

		Blogger blogger = opt.get();
		
		BloggerOutputDto blog = new BloggerOutputDto();
		
		blog.setBloggerName(blogger.getBloggerName());
		blog.setBloggerId(blogger.getBloggerId());
		blog.setKarma(blogger.getKarma());
		
		return blog;
	}

	@Override
	public List<BloggerOutputDto> viewAllBloggers() {
		List<BloggerOutputDto> bloggers = new ArrayList<>();
		
		for(Blogger blogger : blogRepo.findAll()) {
			BloggerOutputDto blog = new BloggerOutputDto();
			
			blog.setBloggerName(blogger.getBloggerName());
			blog.setBloggerId(blogger.getBloggerId());
			blog.setKarma(blogger.getKarma());
			
			bloggers.add(blog);
		}
		
		return bloggers;
	}

	@Override
	public BloggerOutputDto getBloggerByCommentId(int commentId) {
		
		Blogger blog = blogRepo.getBloggerByCommentId(commentId);
		if(blog == null) {
			throw new BloggerIdNotFoundException("No blogger found with comment id: " + commentId);
		}

		// Creating PostOutputDto object
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
					
		// Setting values for bloggerOutputDto by deletedBlogger values
		bloggerOutputDto.setBloggerId(blog.getBloggerId());
		bloggerOutputDto.setBloggerName(blog.getBloggerName());
		bloggerOutputDto.setKarma(blog.getKarma());
		
		return bloggerOutputDto;
	}
	public List<BloggerOutputDto> viewBloggerListByCommunityId(int communityId) {
		
		List<Blogger> bloggers = blogRepo.viewBloggerListByCommunityId(communityId);
		if(bloggers.isEmpty()) {
			throw new BloggerIdNotFoundException("Bloggers not found with the community id:" + communityId);
		}
		List<BloggerOutputDto> allBloggers = new ArrayList<>();
		
		for(Blogger blogger : bloggers) {
			// Creating bloggerOutputDto and setting values
			BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
			
			bloggerOutputDto.setBloggerId(blogger.getBloggerId());
			bloggerOutputDto.setBloggerName(blogger.getBloggerName());
			bloggerOutputDto.setKarma(blogger.getKarma());			
			
			allBloggers.add(bloggerOutputDto);
		}
		
		return allBloggers;
	}

	@Override
	public BloggerOutputDto getBloggerByPostId(int postId) {
		Blogger blogger = blogRepo.getBloggerByPostId(postId);
		if(blogger == null) {
			throw new BloggerIdNotFoundException("Bloggers not found with the post id:" + postId);
		}
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
		
		bloggerOutputDto.setBloggerId(blogger.getBloggerId());
		bloggerOutputDto.setBloggerName(blogger.getBloggerName());
		bloggerOutputDto.setKarma(blogger.getKarma());
		
		return bloggerOutputDto;
	}
	
	@Override
	public List<BloggerOutputDto> getBloggerByAwardId(int awardId) {

		List<Blogger> bloggers = blogRepo.getBloggerByAwardId(awardId);
		if (bloggers.isEmpty()) {
			throw new BloggerIdNotFoundException("Bloggers not found with the Award id:" + awardId);
		}
		List<BloggerOutputDto> allBloggers = new ArrayList<>();

		for (Blogger blogger : bloggers) {
			// Creating bloggerOutputDto and setting values
			BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();

			bloggerOutputDto.setBloggerId(blogger.getBloggerId());
			bloggerOutputDto.setBloggerName(blogger.getBloggerName());
			bloggerOutputDto.setKarma(blogger.getKarma());

			allBloggers.add(bloggerOutputDto);
		}

		return allBloggers;
	}

	@Override
	public BloggerOutputDto getBloggerByUserId(int userId) {
		
		Blogger blogger = blogRepo.getBloggerByUserId(userId);
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();

		bloggerOutputDto.setBloggerId(blogger.getBloggerId());
		bloggerOutputDto.setBloggerName(blogger.getBloggerName());
		bloggerOutputDto.setKarma(blogger.getKarma());
		return bloggerOutputDto;
	}

}
