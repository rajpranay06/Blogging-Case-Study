package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.exception.PostIdNotFoundException;
import com.example.demo.exception.PostTypeInvalidException;
import com.example.demo.repository.IPostRepository;

@Service
public class PostServiceImpl implements IPostService {
	
	@Autowired
	IPostRepository postRepo;

	@Override
	public PostOutputDto addPost(PostInputDto post) {
		
		// Saving the post in the database
		PostType postType = post.getContent();
		if(!(postType.equals(PostType.TEXT) || postType.equals(PostType.LINK) || postType.equals(PostType.POLL) || postType.equals(PostType.VIDEO_IMAGE)))
			throw new PostTypeInvalidException("Post Type should be TEXT or LINK or POLL or VIDEO_IMAGE");
		
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
		
		// Saving the post in database
		Post addedPost = postRepo.save(newPost);
		
		// Creating PostOutputDto
		PostOutputDto postOutputDto = new PostOutputDto();
		
		// Setting values for postOutputDto by addedPost values
		postOutputDto.setPostId(addedPost.getPostId());
		postOutputDto.setTitle(addedPost.getTitle());
		postOutputDto.setContent(addedPost.getContent());
		postOutputDto.setCreatedDateTime(addedPost.getCreatedDateTime());
		postOutputDto.setFlair(addedPost.getFlair().substring(1));
		postOutputDto.setNotSafeForWork(addedPost.isNotSafeForWork());
		postOutputDto.setOriginalContent(addedPost.isOriginalContent());
		postOutputDto.setVotes(addedPost.getVotes());
		postOutputDto.setVoteUp(addedPost.isVoteUp());
		postOutputDto.setSpoiler(addedPost.isSpoiler());
		
		return postOutputDto;
	}

	@Override
	public PostOutputDto updatePost(PostInputDto post) {
		
		// Finding the post by id
		Optional<Post> opt = postRepo.findById(post.getPostId());
		if(!opt.isPresent()) {
			 // If post is not present throw an error
			 throw new PostIdNotFoundException("No post with id: " + post.getPostId());
		}
		// If post is present update the oldPost with new Post
		Post oldPost = opt.get();
		 
		// Assigning PostType to check if valid or not 
		PostType postType = post.getContent();
			if(!(postType.equals(PostType.TEXT) || postType.equals(PostType.LINK) || postType.equals(PostType.POLL) || postType.equals(PostType.VIDEO_IMAGE)))
				throw new PostTypeInvalidException("Post Type should be TEXT or LINK or POLL or VIDEO_IMAGE");
		 
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
		 
		Post updatedPost = postRepo.save(oldPost);
		 
		// Creating PostOutputDto
		PostOutputDto postOutputDto = new PostOutputDto();
			
		// Setting values for postOutputDto by updatedPost values
		postOutputDto.setPostId(updatedPost.getPostId());
		postOutputDto.setTitle(updatedPost.getTitle());
		postOutputDto.setContent(updatedPost.getContent());
		postOutputDto.setCreatedDateTime(updatedPost.getCreatedDateTime());
		postOutputDto.setFlair(updatedPost.getFlair().substring(1));
		postOutputDto.setNotSafeForWork(updatedPost.isNotSafeForWork());
		postOutputDto.setOriginalContent(updatedPost.isOriginalContent());
		postOutputDto.setVotes(updatedPost.getVotes());
		postOutputDto.setVoteUp(updatedPost.isVoteUp());
		postOutputDto.setSpoiler(updatedPost.isSpoiler());
			
		return postOutputDto;
	}

	@Override
	public PostOutputDto deletePost(int id) {
		
		// Finding the post by id
		Optional<Post> opt = postRepo.findById(id);
		if(!opt.isPresent()) {
			 // If post is not present throw an error
			 throw new PostIdNotFoundException("No post with id: " + id);
		}
		Post deletedPost = opt.get();
		postRepo.delete(deletedPost);
		
		// Creating PostOutputDto
		PostOutputDto postOutputDto = new PostOutputDto();
			
		// Setting values for postOutputDto by deletedPost values
		postOutputDto.setPostId(deletedPost.getPostId());
		postOutputDto.setTitle(deletedPost.getTitle());
		postOutputDto.setContent(deletedPost.getContent());
		postOutputDto.setCreatedDateTime(deletedPost.getCreatedDateTime());
		postOutputDto.setFlair(deletedPost.getFlair().substring(1));
		postOutputDto.setNotSafeForWork(deletedPost.isNotSafeForWork());
		postOutputDto.setOriginalContent(deletedPost.isOriginalContent());
		postOutputDto.setVotes(deletedPost.getVotes());
		postOutputDto.setVoteUp(deletedPost.isVoteUp());
		postOutputDto.setSpoiler(deletedPost.isSpoiler());
			
		return postOutputDto;
	}

	@Override
	public List<PostOutputDto> getPostBySearchString(String searchStr) {
		
		// Concatenate % to the string to find all the titles with the including string
		searchStr = '%' + searchStr + '%';
		
		// Creating a list of PostOutputDto
		List<PostOutputDto> allPosts = new ArrayList<>();
		
		for(Post post : postRepo.getPostBySearchString(searchStr)) {
			
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
			
			allPosts.add(postOutputDto);
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

}
