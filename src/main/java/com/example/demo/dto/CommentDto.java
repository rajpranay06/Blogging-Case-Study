package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentDto {
	private int commentId;
	private String commentDescription;
	private int votes;
	private boolean voteUp;
	private PostOutputDto post;
}
