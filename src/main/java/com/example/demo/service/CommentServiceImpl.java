package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Blogger;
import com.example.demo.bean.Comment;
import com.example.demo.bean.Moderator;
import com.example.demo.bean.Post;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.exception.ModeratorApprovalException;
import com.example.demo.exception.PostIdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.repository.IPostRepository;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	IPostRepository postRepo;
	
	@Autowired
	ICommentRepository comRepo;
	
	@Autowired
	IBloggerRepository blogRepo;
	
	Moderator moderator = new Moderator();

	@Override
	public CommentDto addComment(Comment comment) {
		
		Comment com = comRepo.save(comment);
		
		// Setting comment variables by commentOutputDto values
		CommentDto comDto = new CommentDto();
		comDto.setCommentId(com.getCommentId());
		comDto.setCommentDescription(com.getCommentDescription());
		comDto.setVotes(com.getVotes());
		comDto.setVoteUp(com.isVoteUp());
		
		Post post = comment.getPost();
		
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
		
		comDto.setPost(postOutputDto);
		
		// Creating Blogger object
		Blogger blogger = comment.getBlogger();
		
		// Creating BloggerOutputDto
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
		
		// Setting values for BloggerOutputDto
		bloggerOutputDto.setBloggerId(blogger.getBloggerId());
		bloggerOutputDto.setBloggerName(blogger.getBloggerName());
		bloggerOutputDto.setKarma(blogger.getKarma());
		
		comDto.setBlogger(bloggerOutputDto);
		
		return comDto;
	}

	@Override
	public void deleteComment(int id) {
		//Check whether comment is available in DB or not by using Id
		Optional<Comment> opt = comRepo.findById(id);
		if(!opt.isPresent()) {
			throw new CommentNotFoundException("Comment Not Found");
		}
		Comment comment = opt.get();
		//Delete Comment
		comRepo.delete(comment);
	}

	@Override
	public void upVote(int commentId, boolean upVote) {
		//Check whether comment is available in DB or not by using Id
		Optional<Comment> opt = comRepo.findById(commentId);
		if(!opt.isPresent()) {
			throw new CommentNotFoundException("Comment Not Found with the given id: " +commentId);
		}
		Comment com = opt.get();
		//set vote value
		com.setVoteUp(upVote);
		comRepo.save(com);
	}

	@Override
	public CommentDto addCommentDto(CommentInputDto commentInputDto) {
		
		// Checking for moderator approval
		if(!moderator.moderatesPostsAndComments(commentInputDto.isFlag())) {
			throw new ModeratorApprovalException("Comment is not approved");
		}
		
		// Creating Comment object
		Comment com = new Comment();
		
		// Setting Comment variables by CommentInputDto values
		com.setCommentDescription(commentInputDto.getCommentDescription());
		com.setVotes(commentInputDto.getVotes());
		com.setVoteUp(commentInputDto.isVoteUp());
		
		//Get the post with the id
		Optional<Post> opt1 = postRepo.findById(commentInputDto.getPostId());
		if(!opt1.isPresent()) {
			throw new PostIdNotFoundException("No post is found with id:" + commentInputDto.getPostId());
		}
		Post post = opt1.get();
		com.setPost(post);
		
		//Get the Blogger with the id
		Optional<Blogger> opt2 = blogRepo.findById(commentInputDto.getBloggerId());
		if(!opt2.isPresent()) {
			throw new IdNotFoundException("No blogger is found with id:" + commentInputDto.getBloggerId());
		}
		Blogger blogger = opt2.get();
		
		// Set the blogger to comment
		com.setBlogger(blogger);
		
		//save the comment in DB
		Comment newCom = comRepo.save(com);
		
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
		
		// Creating BloggerOutputDto
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
		
		// Setting values for BloggerOutputDto
		bloggerOutputDto.setBloggerId(blogger.getBloggerId());
		bloggerOutputDto.setBloggerName(blogger.getBloggerName());
		bloggerOutputDto.setKarma(blogger.getKarma());
		
		CommentDto comDto = new CommentDto();
		comDto.setCommentId(newCom.getCommentId());
		comDto.setCommentDescription(newCom.getCommentDescription());
		comDto.setVotes(newCom.getVotes());
		comDto.setVoteUp(newCom.isVoteUp());
		comDto.setPost(postOutputDto);
		comDto.setBlogger(bloggerOutputDto);
		
		return comDto;
	}

	@Override
	public CommentOutputDto getCommentById(int id) {
		//Check whether comment is available in DB or not by using Id
		Optional<Comment> opt = comRepo.findById(id);
		if(!opt.isPresent()) {
			throw new CommentNotFoundException("Comment Not Found with the given id: " + id);
		}
		
		Comment com = opt.get();
		
		// Creating comment output dto object
		CommentOutputDto commentOutput = new CommentOutputDto();
		
		// setting values
		commentOutput.setCommentId(com.getCommentId());
		commentOutput.setCommentDescription(com.getCommentDescription());
		commentOutput.setVotes(com.getVotes());
		commentOutput.setVoteUp(com.isVoteUp());
		
		return commentOutput;
	}

	@Override
	public List<CommentOutputDto> listAllCommentsOfPost(int postId) {
		
		List<Comment> comments = comRepo.getAllCommentsOfPost(postId);
		
		if(comments.isEmpty()) {
			 // No comments are found for post
			throw new CommentNotFoundException("No comments for the post with post id: " + postId);
		}
		
		List<CommentOutputDto> allComments = new ArrayList<>();
		
		for(Comment comment : comments) {
			
			// Comment Object created
			CommentOutputDto com = new CommentOutputDto();
			
			com.setCommentId(comment.getCommentId());
			com.setCommentDescription(comment.getCommentDescription());
			com.setVotes(comment.getVotes());
			com.setVoteUp(comment.isVoteUp());
			
			allComments.add(com);
		}
		
		return allComments;
	}
	
	public List<CommentOutputDto> listAllCommentsOfBlogger(int bloggerId) {
		
		List<Comment> comments = comRepo.getCommentsByBlogger(bloggerId);
		
		if(comments.isEmpty()) {
			 // No comments are found for post
			throw new CommentNotFoundException("No comments for the blogger with blogger id: " + bloggerId);
		}
		
		List<CommentOutputDto> allComments = new ArrayList<>();
		
		for(Comment comment : comments) {
			
			// Comment Object created
			CommentOutputDto com = new CommentOutputDto();
			
			com.setCommentId(comment.getCommentId());
			com.setCommentDescription(comment.getCommentDescription());
			com.setVotes(comment.getVotes());
			com.setVoteUp(comment.isVoteUp());
			
			allComments.add(com);
		}
		
		return allComments;
	}

	@Override
	public CommentOutputDto updateComment(CommentInputDto comment) {
		
		// Checking for moderator approval
		if(!moderator.moderatesPostsAndComments(comment.isFlag())) {
			throw new ModeratorApprovalException("Comment is not approved");
		}
				
		// Creating Comment object
		Optional<Comment> opt = comRepo.findById(comment.getCommentId());
		if(!opt.isPresent()) {
			throw new CommentNotFoundException("No comments for the comment ID: " + comment.getCommentId());
		}
		
		Comment com = opt.get();
		
		// Setting Comment variables by CommentInputDto values
		com.setCommentDescription(comment.getCommentDescription());
		com.setVotes(comment.getVotes());
		com.setVoteUp(comment.isVoteUp());
		
		//Get the post with the id
		Optional<Post> opt1 = postRepo.findById(comment.getPostId());
		if(!opt1.isPresent()) {
			throw new PostIdNotFoundException("No post is found with id:" + comment.getPostId());
		}
		Post post = opt1.get();
		com.setPost(post);
		

		//Get the Blogger with the id
		Optional<Blogger> opt2 = blogRepo.findById(comment.getBloggerId());
		if(!opt2.isPresent()) {
			throw new IdNotFoundException("No blogger is found with id:" + comment.getBloggerId());
		}
		Blogger blogger = opt2.get();
		com.setBlogger(blogger);
		
		//save the comment in DB
		Comment newCom = comRepo.save(com);
		
		CommentOutputDto comDto = new CommentOutputDto();
		comDto.setCommentId(newCom.getCommentId());
		comDto.setCommentDescription(newCom.getCommentDescription());
		comDto.setVotes(newCom.getVotes());
		comDto.setVoteUp(newCom.isVoteUp());
		
		return comDto;
	}
	
}