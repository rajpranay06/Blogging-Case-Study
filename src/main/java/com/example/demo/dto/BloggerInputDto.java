package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BloggerInputDto {
	
	private int userId;
	@NotEmpty(message="Name shouldn't be empty")
	@Size(min=3, max=50, message="Min 3 characters required")
	private String bloggerName;
	private int karma;
	
	private List<Integer> commentIds;

}
