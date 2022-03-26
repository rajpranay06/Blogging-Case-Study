package com.example.demo.service;

import com.example.demo.bean.Comment;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentInputDto;
import com.example.demo.dto.CommentOutputDto;

public interface ICommentService {

	public CommentDto addComment(Comment comment);
	public CommentOutputDto addCommentDto(CommentInputDto comment);
	public void deleteComment(Comment comment);
	public void upVote(int commentId, boolean upVote);
}
