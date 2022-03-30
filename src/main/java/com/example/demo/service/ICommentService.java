package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Comment;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;

public interface ICommentService {

	public CommentDto addComment(Comment comment);
	public CommentDto addCommentDto(CommentInputDto comment);
	public void deleteComment(int id);
	public void upVote(int commentId, boolean upVote);
	public CommentOutputDto getCommentById(int id);
	public List<CommentOutputDto> listAllCommentsOfPost(int postId);
	public List<CommentOutputDto> listAllCommentsOfBlogger(int userId);
	public CommentOutputDto updateComment(CommentInputDto comment);
}