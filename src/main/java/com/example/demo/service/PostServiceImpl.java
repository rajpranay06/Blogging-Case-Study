package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.bean.Award;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Post;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Community;
import com.example.demo.bean.Moderator;
import com.example.demo.bean.Post;
import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.exception.AwardNotFoundException;
import com.example.demo.exception.BloggerIdNotFoundException;
import com.example.demo.exception.PostIdNotFoundException;
import com.example.demo.repository.IPostRepository;
import com.example.demo.repository.IAwardRepository;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.exception.ModeratorApprovalException;
import com.example.demo.exception.PostIdNotFoundException;
import com.example.demo.repository.IPostRepository;
import com.example.demo.repository.IAwardRepository;
import com.example.demo.repository.ICommunityRepository;
@Service
public class PostServiceImpl implements IPostService {
	
	@Autowired
	IPostRepository postRepo;
	
	@Autowired
	ICommunityRepository comRepo;
	
	@Autowired
	IAwardRepository awardRepo;
	
	@Autowired
	IBloggerRepository blogRepo;
	// Creating Moderator object
	Moderator moderator = new Moderator();
	@Override
	public Post addPostWithoutDto(Post post) {
		return postRepo.save(post);
	}
	@Override
	public PostDto addPost(PostInputDto post) {
		
		// Checking for Moderator Approval
		if(!moderator.moderatesPostsAndComments(post.isFlag())) {
			throw new ModeratorApprovalException("Post is not approved");
		}
		
		// Creating post object
		Post newPost = new Post();
		
		// Setting post variables by postInputDto values
		newPost.setTitle(post.getTitle());
		newPost.setContent(post.getContent());
		newPost.setCreatedDateTime(post.getCreatedDateTime());
		newPost.setFlair('#' + post.getFlair());
		newPost.setNotSafeForWork(post.isNotSafeForWork());
		newPost.setOriginalContent(post.isOriginalContent());
		newPost.setVotes(post.getVotes());
		newPost.setVoteUp(post.isVoteUp());
		newPost.setSpoiler(post.isSpoiler());
		
		// Getting awards by award ID
		List<Award> awards = new ArrayList<>();
		for(Integer id : post.getAwardIds()) {
			System.out.println(id);
			Optional<Award> opt = awardRepo.findById(id);
			if(opt.isPresent()) {
				awards.add(opt.get());
			}
			else {
				throw new PostIdNotFoundException("Not found any award with id: " + id);
			}
		}
		
		// Setting the awards to the post
		newPost.setAwards(awards);
		
		//getting bloggerId
		Blogger blogger=new Blogger();
		Optional<Blogger> opt = blogRepo.findById(post.getBloggerId());
		
		if(!opt.isPresent())
		{
			throw new BloggerIdNotFoundException("Blogger not found");
		}
		//setting bloggerId to post
		newPost.setBlogger(opt.get());
		// Getting community by id
		Optional<Community> opt = comRepo.findById(post.getCommunityId());
		
		if(!opt.isPresent()) {
			throw new PostIdNotFoundException("No Community with id: " + post.getCommunityId());
		}
		newPost.setCommunity(opt.get());

		// Saving the post in database
		Post addedPost = postRepo.save(newPost);
		
		// Creating PostDto object
		PostDto postDto = new PostDto();
		
		// Setting values for postOutputDto
		postDto.setPostId(addedPost.getPostId());
		postDto.setTitle(addedPost.getTitle());
		postDto.setContent(addedPost.getContent());
		postDto.setCreatedDateTime(addedPost.getCreatedDateTime());
		postDto.setFlair(addedPost.getFlair().substring(1));
		postDto.setNotSafeForWork(addedPost.isNotSafeForWork());
		postDto.setOriginalContent(addedPost.isOriginalContent());
		postDto.setVotes(addedPost.getVotes());
		postDto.setVoteUp(addedPost.isVoteUp());
		postDto.setSpoiler(addedPost.isSpoiler());
		postDto.setCommunity(addedPost.getCommunity());
		
		return postDto;
	}

	@Override
	public PostDto updatePost(PostInputDto post) {
		
		// Checking for Moderator Approval
		if(!moderator.moderatesPostsAndComments(post.isFlag())) {
			throw new ModeratorApprovalException("Post is not approved");
		}
		
		// Finding the post by id
		Optional<Post> opt = postRepo.findById(post.getPostId());
		if(!opt.isPresent()) {
			 // If post is not present throw an error
			 throw new PostIdNotFoundException("No post with id: " + post.getPostId());
		}
		// If post is present update the oldPost with new Post
		Post oldPost = opt.get();
	
	    // Assigning values to oldPost
		oldPost.setTitle(post.getTitle());
		oldPost.setContent(post.getContent());
		oldPost.setCreatedDateTime(post.getCreatedDateTime());
		oldPost.setFlair('#' + post.getFlair());
		oldPost.setNotSafeForWork(post.isNotSafeForWork());
		oldPost.setOriginalContent(post.isOriginalContent());
		oldPost.setVotes(post.getVotes());
		oldPost.setVoteUp(post.isVoteUp());
		oldPost.setSpoiler(post.isSpoiler());
		
		// Creating List of Award Objects to store awards 
		List<Award> awards = new ArrayList<>();
		
		for(Integer id: post.getAwardIds()) {
			System.out.println(id);
			Optional<Award> opt1 = awardRepo.findById(id);
			if(opt1.isPresent()) {
				awards.add(opt1.get());
			}
			else {
				throw new AwardNotFoundException("Not found any award with id: " + id);
			}
		}
		// Setting the awards
		oldPost.setAwards(awards);
		
		//getting bloggerId
		Blogger blogger=new Blogger();
		Optional<Blogger> opt1 = blogRepo.findById(post.getBloggerId());
		if(!opt1.isPresent())
		{
			throw new BloggerIdNotFoundException("Blogger not found");
		}
		//setting bloggerId to post
		oldPost.setBlogger(opt1.get());
		
		return postRepo.save(oldPost);
		// Getting community by id
		Optional<Community> community = comRepo.findById(post.getCommunityId());
		
		if(!community.isPresent()) {
			throw new PostIdNotFoundException("No Community with id: " + post.getCommunityId());
		}
		
		// Setting the community
		oldPost.setCommunity(community.get());
		
		// Saving the post in database
		Post updatedPost = postRepo.save(oldPost);
		
		// Creating PostDto object
		PostDto postDto = new PostDto();
		
		// Setting values for postOutputDto
		postDto.setPostId(updatedPost.getPostId());
		postDto.setTitle(updatedPost.getTitle());
		postDto.setContent(updatedPost.getContent());
		postDto.setCreatedDateTime(updatedPost.getCreatedDateTime());
		postDto.setFlair(updatedPost.getFlair().substring(1));
		postDto.setNotSafeForWork(updatedPost.isNotSafeForWork());
		postDto.setOriginalContent(updatedPost.isOriginalContent());
		postDto.setVotes(updatedPost.getVotes());
		postDto.setVoteUp(updatedPost.isVoteUp());
		postDto.setSpoiler(updatedPost.isSpoiler());
		postDto.setCommunity(updatedPost.getCommunity());
		
		return postDto;
	}

	@Override
	public void deletePost(int id) {
		
		// Finding the post by id
		Optional<Post> opt = postRepo.findById(id);
		if(opt.isPresent()) {
			Post deletedPost = opt.get();
			// Calling delete function in postRepo
			postRepo.delete(deletedPost);
		}
		else {
			// If post is not present throw an error
			throw new PostIdNotFoundException("No post with id: " + id);
		}
		
	}

	@Override
	public List<PostOutputDto> getPostBySearchString(String searchStr) {
		
		// Concatenate % to the string to find all the titles with the including string
		String searchString = '%' + searchStr + '%';
		
		// Creating a list of PostOutputDto
		List<Post> allPosts = postRepo.getPostBySearchString(searchString);
		
		// Checking if posts are empty 
		if(allPosts.isEmpty()) {
			throw new PostIdNotFoundException("No post with search string: " + searchStr);
		}
		
		// Creating List of posts
		List<PostOutputDto> posts = new ArrayList<>();
		
		for(Post post : allPosts) {
			// Creating PostDto object
			PostOutputDto postDto = new PostOutputDto();
			
			// Setting values for postOutputDto
			postDto.setPostId(post.getPostId());
			postDto.setTitle(post.getTitle());
			postDto.setContent(post.getContent());
			postDto.setCreatedDateTime(post.getCreatedDateTime());
			postDto.setFlair(post.getFlair().substring(1));
			postDto.setNotSafeForWork(post.isNotSafeForWork());
			postDto.setOriginalContent(post.isOriginalContent());
			postDto.setVotes(post.getVotes());
			postDto.setVoteUp(post.isVoteUp());
			postDto.setSpoiler(post.isSpoiler());
			postDto.setAwards(post.getAwards());
			
			posts.add(postDto);
		}
		
		return posts;
	}

	@Override
	public void upVote(int postId, boolean upVote) {
		// Finding the post by id
		Optional<Post> opt = postRepo.findById(postId);
		if(!opt.isPresent()) {
			// If post is not present throw an error
			throw new PostIdNotFoundException("No post with id: " + postId);
		}
		Post post = opt.get();
		
		// Setting the voteUp variable 
		post.setVoteUp(upVote);
				
		postRepo.save(post);
		
	}
	
	@Override
	public List<PostDto> getPostsByBloggerId(int bloggerId) {
		
		List<PostDto> allPosts = new ArrayList<>();
		
		List<Post> posts = postRepo.getPostsByBloggerId(bloggerId);
		
		if(posts.isEmpty())
		{
			throw new PostIdNotFoundException("No post found for the blogger with id: "+ bloggerId);
		}
		
		for(Post post : posts) {
			
			// Creating PostOutputDto object
			PostDto postOutputDto = new PostDto();
			
			// Setting values for postOutputDto
			postOutputDto.setPostId(post.getPostId());
			postOutputDto.setTitle(post.getTitle());
			postOutputDto.setContent(post.getContent());
			postOutputDto.setCreatedDateTime(post.getCreatedDateTime());
			postOutputDto.setVotes(post.getVotes());
			postOutputDto.setVoteUp(post.isVoteUp());
			postOutputDto.setNotSafeForWork(post.isNotSafeForWork());
			postOutputDto.setSpoiler(post.isSpoiler());
			postOutputDto.setOriginalContent(post.isOriginalContent());
			postOutputDto.setFlair(post.getFlair());
			postOutputDto.setAwards(post.getAwards());
			
			allPosts.add(postOutputDto);
		}
		return allPosts;
	}
		
	@Override
	public List<PostOutputDto> getPostByawardId(int id) {
		
		// Getting all the posts with award id
		List<Post> posts = postRepo.getAllPostsByAwardId(id);
		
		if(posts.isEmpty())
		{
			throw new PostIdNotFoundException("No post found for the award with id: "+ id);
		}
		
		// Creating a list of postOutputDto object
		List<PostOutputDto> allPosts = new ArrayList<>();
		
		for(Post p : posts) {
			PostOutputDto postOutputDto = new PostOutputDto();
			postOutputDto.setPostId(p.getPostId());
			postOutputDto.setTitle(p.getTitle());
			postOutputDto.setContent(p.getContent());
			postOutputDto.setCreatedDateTime(p.getCreatedDateTime());
			postOutputDto.setVotes(p.getVotes());
			postOutputDto.setVoteUp(p.isVoteUp());
			postOutputDto.setNotSafeForWork(p.isNotSafeForWork());
			postOutputDto.setSpoiler(p.isSpoiler());
			postOutputDto.setOriginalContent(p.isOriginalContent());
			postOutputDto.setFlair(p.getFlair());
			postOutputDto.setAwards(p.getAwards());
			
			allPosts.add(postOutputDto);
		}
		return allPosts;
	}

	public List<PostOutputDto> listPostsByCommunityId(int communityId) {
		
		List<Post> posts = postRepo.getAllPostsByCommunityId(communityId);
		
		//Find whether community has posts or not
		if(posts.isEmpty())
		{
			throw new PostIdNotFoundException("No post found for the community with id: "+ communityId);
		}
		
		// Creating a list of postOutputDto object
		List<PostOutputDto> allPosts = new ArrayList<>();
		
		for(Post p : posts) {
			
			PostOutputDto postOutputDto = new PostOutputDto();
			
			postOutputDto.setPostId(p.getPostId());
			postOutputDto.setTitle(p.getTitle());
			postOutputDto.setContent(p.getContent());
			postOutputDto.setCreatedDateTime(p.getCreatedDateTime());
			postOutputDto.setVotes(p.getVotes());
			postOutputDto.setVoteUp(p.isVoteUp());
			postOutputDto.setNotSafeForWork(p.isNotSafeForWork());
			postOutputDto.setSpoiler(p.isSpoiler());
			postOutputDto.setOriginalContent(p.isOriginalContent());
			postOutputDto.setFlair(p.getFlair());
			postOutputDto.setAwards(p.getAwards());
			
			allPosts.add(postOutputDto);
		}
		return allPosts;
	}
	public PostOutputDto getPostByCommentId(int commentId) {
		Post post = postRepo.getPostByCommentId(commentId);
		if(post == null) {
			throw new PostIdNotFoundException("No post found with comment id: " + commentId);
		}

		// Creating PostOutputDto object
		PostOutputDto postOutputDto = new PostOutputDto();
					
		// Setting values for postOutputDto by deletedPost values
		postOutputDto.setPostId(post.getPostId());
		postOutputDto.setTitle(post.getTitle());
		postOutputDto.setContent(post.getContent());
		postOutputDto.setCreatedDateTime(post.getCreatedDateTime());
		postOutputDto.setFlair(post.getFlair().substring(1));
		postOutputDto.setNotSafeForWork(post.isNotSafeForWork());
		postOutputDto.setOriginalContent(post.isOriginalContent());
		postOutputDto.setVotes(post.getVotes());
		postOutputDto.setVoteUp(post.isVoteUp());
		postOutputDto.setSpoiler(post.isSpoiler());
		postOutputDto.setAwards(post.getAwards());
		
		return postOutputDto;
	}
	
	@Override
	public List<PostOutputDto> getUpvotedPostsOfBlogger(int bloggerId) {

		// Creating a list of postOutputDto
		List<PostOutputDto> allPosts = new ArrayList<>();
		
		List<Post> posts = postRepo.getUpvotedPostsOfBlogger(bloggerId);
		
		if(posts.isEmpty())
		{
			throw new PostIdNotFoundException("No upvoted post found for the blogger with id: "+ bloggerId);
		}
		
		for(Post post : posts) {
			
			// Creating PostOutputDto object
			PostOutputDto postOutputDto = new PostOutputDto();
			
			// Setting values for postOutputDto
			postOutputDto.setPostId(post.getPostId());
			postOutputDto.setTitle(post.getTitle());
			postOutputDto.setContent(post.getContent());
			postOutputDto.setCreatedDateTime(post.getCreatedDateTime());
			postOutputDto.setFlair(post.getFlair().substring(1));
			postOutputDto.setNotSafeForWork(post.isNotSafeForWork());
			postOutputDto.setOriginalContent(post.isOriginalContent());
			postOutputDto.setVotes(post.getVotes());
			postOutputDto.setVoteUp(post.isVoteUp());
			postOutputDto.setSpoiler(post.isSpoiler());
			postOutputDto.setAwards(post.getAwards());
			
			allPosts.add(postOutputDto);
		}
		return allPosts;
	}

}
