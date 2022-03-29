package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Comment;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;

public interface ICommentService {

	public CommentOutputDto addComment(Comment comment);
	public CommentOutputDto addCommentDto(CommentInputDto comment);
	public void deleteComment(int id);
	public void upVote(int commentId, boolean upVote);
	public Comment getCommentById(int id);
	public List<Comment> listAllCommentsOfPost(int postId);
	public List<Comment> listAllCommentsOfBlogger(int userId);
}