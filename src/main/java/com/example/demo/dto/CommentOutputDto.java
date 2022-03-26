package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentOutputDto {

	private int commentId;
	private String commentDescription;
	private int votes;
	private boolean voteUp;
}
