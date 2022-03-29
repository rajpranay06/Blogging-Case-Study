package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Comment;
import com.example.demo.bean.Post;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;
import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.exception.PostIdNotFoundException;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.repository.IPostRepository;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	IPostRepository postRepo;
	
	@Autowired
	ICommentRepository comRepo;

	@Override
	public CommentOutputDto addComment(Comment comment) {
		
		Comment com = comRepo.save(comment);
		// Setting comment variables by commentOutputDto values
		CommentOutputDto comDto = new CommentOutputDto();
		comDto.setCommentId(com.getCommentId());
		comDto.setCommentDescription(com.getCommentDescription());
		comDto.setVotes(com.getVotes());
		comDto.setVoteUp(com.isVoteUp());
		return comDto;
	}

	@Override
	public Comment deleteComment(int id) {
		//Check whether comment is available in DB or not by using Id
		Optional<Comment> opt = comRepo.findById(id);
		if(!opt.isPresent()) {
			throw new CommentNotFoundException("Comment Not Found");
		}
		Comment comment = opt.get();
		//Delete Comment
		comRepo.delete(comment);
		return comment;
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
	public CommentOutputDto addCommentDto(CommentInputDto commentInputDto) {
		// Creating Comment object
		Comment com = new Comment();
		
		// Setting Comment variables by CommentInputDto values
		com.setCommentDescription(commentInputDto.getCommentDescription());
		com.setVotes(commentInputDto.getVotes());
		com.setVoteUp(commentInputDto.isVoteUp());
		//save the comment in DB
		Comment newCom = comRepo.save(com);
		CommentOutputDto comOutputDto = new CommentOutputDto();
		comOutputDto.setCommentDescription(newCom.getCommentDescription());
		comOutputDto.setVotes(newCom.getVotes());
		comOutputDto.setVoteUp(newCom.isVoteUp());
		
		return comOutputDto;
	}

	@Override
	public Comment getCommentById(int id) {
		//Check whether comment is available in DB or not by using Id
		Optional<Comment> opt = comRepo.findById(id);
		if(!opt.isPresent()) {
			throw new CommentNotFoundException("Comment Not Found with the given id: " + id);
		}
		return opt.get();
	}

	@Override
	public List<Comment> listAllCommentsOfPost(int postId) {
		
		Optional<Post> opt = postRepo.findById(postId);
		if(!opt.isPresent()) {
			 // If post is not present throw an error
			 throw new PostIdNotFoundException("No post with id: " + postId);
		}
		Post postById = opt.get();
		if(postById.getComments().isEmpty()) {
			throw new CommentNotFoundException("No comments for the post with post id: " + postId);
		}
		return postById.getComments();
	}
	
}