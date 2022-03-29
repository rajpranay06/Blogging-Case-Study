package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Award;
import com.example.demo.bean.Comment;
import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.exception.PostIdNotFoundException;
import com.example.demo.exception.PostTypeInvalidException;
import com.example.demo.repository.IPostRepository;
import com.example.demo.repository.IAwardRepository;
import com.example.demo.repository.ICommentRepository;

@Service
public class PostServiceImpl implements IPostService {
	
	@Autowired
	IPostRepository postRepo;
	
	@Autowired
	ICommentRepository commentRepo;
	
	@Autowired
	IAwardRepository awardRepo;

	@Override
	public Post addPostWithoutDto(Post post) {
		return postRepo.save(post);
	}
	@Override
	public Post addPost(PostInputDto post) {
		
		// Getting the post type enum
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
		
		// Creating a list of comments
		List<Comment> comments = new ArrayList<>();
		
		// Getting comments from the Comment Entity by using ids
		for(Integer id : post.getCommentIds()) {
			System.out.println(id);
			Comment comment = commentRepo.findById(id).get();
			System.out.println(comment);
			comments.add(comment);
		}
		
		newPost.setComments(comments);
		System.out.println(newPost);
		
		List<Award> awards = new ArrayList<>();
		for(Integer id : post.getAwardIds()) {
			System.out.println(id);
			Award award = awardRepo.findById(id).get();
			System.out.println(award);
			awards.add(award);
		}
		newPost.setAwards(awards);
		System.out.println(newPost);
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
		
		// Creating a list of comments
		List<Comment> comments = new ArrayList<>();
				
		// Getting comments from the Comment Entity by using ids
		for(Integer id : post.getCommentIds()) {
			comments.add(commentRepo.findById(id).get());
		}
				
		oldPost.setComments(comments);	
		
		List<Award> awards = new ArrayList<>();
		
		for(Integer id: post.getAwardIds()) {
			awards.add(awardRepo.findById(id).get());
		}
		
		oldPost.setAwards(awards);
		
		return postRepo.save(oldPost);
		
	}

	@Override
	public Post deletePost(int id) {
		
		// Finding the post by id
		Optional<Post> opt = postRepo.findById(id);
		if(!opt.isPresent()) {
			 // If post is not present throw an error
			 throw new PostIdNotFoundException("No post with id: " + id);
		}
		Post deletedPost = opt.get();
		
		// Calling delete function in postRepo
		postRepo.delete(deletedPost);
		
		return deletedPost;
		
	}

	@Override
	public List<Post> getPostBySearchString(String searchStr) {
		
		// Concatenate % to the string to find all the titles with the including string
		String searchString = '%' + searchStr + '%';
		// Creating a list of Post
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
	public List<PostOutputDto> getPostByawardId(int id) {
		List<Post> posts = postRepo.getAllPostsByAwardId(id);
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

	
	
}
