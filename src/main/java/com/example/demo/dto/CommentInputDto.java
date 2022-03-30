package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class CommentInputDto {

	private int commentId;
	@Size(min = 2, max=20, message = "Comment of this size is not allowed")
	@NotEmpty
	private String commentDescription;
	private int votes;
	private boolean voteUp;
	private Integer postId;
	
	// Boolean flag to get the approval of moderator
	// Flag should be false to add the comment to the database
	private boolean flag;
  
}