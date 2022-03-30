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

@Service
public class PostServiceImpl implements IPostService {
	
	@Autowired
	IPostRepository postRepo;
	
	@Autowired
	ICommentRepository commentRepo;
	
	@Autowired
	IAwardRepository awardRepo;
	
	@Autowired
	IBloggerRepository blogRepo;
	
	@Override
	public Post addPostWithoutDto(Post post) {
		return postRepo.save(post);
	}
	@Override
	public Post addPost(PostInputDto post) {
		
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
			Award award = awardRepo.findById(id).get();
			System.out.println(award);
			awards.add(award);
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

		// Saving the post in database
		return postRepo.save(newPost);
	}

	@Override
	public Post updatePost(PostInputDto post) {
		
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
			if(!opt1.isPresent()) {
				throw new AwardNotFoundException("Not found any award with id: " + id);
			}
			awards.add(opt1.get());
			
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
	}

	@Override
	public void deletePost(int id) {
		
		// Finding the post by id
		Optional<Post> opt = postRepo.findById(id);
		if(!opt.isPresent()) {
			 // If post is not present throw an error
			 throw new PostIdNotFoundException("No post with id: " + id);
		}
		Post deletedPost = opt.get();
		
		// Calling delete function in postRepo
		postRepo.delete(deletedPost);
	}

	@Override
	public List<Post> getPostBySearchString(String searchStr) {
		
		// Concatenate % to the string to find all the titles with the including string
		String searchString = '%' + searchStr + '%';
		
		// Creating a list of PostOutputDto
		List<Post> allPosts = postRepo.getPostBySearchString(searchString);
		
		if(allPosts.isEmpty()) {
			throw new PostIdNotFoundException("No post with search string: " + searchStr);
		}
		return allPosts;
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
		
		return postOutputDto;
	}

}
