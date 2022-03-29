package com.example.demo.dto;

import java.util.List;

import com.example.demo.bean.Post;

import lombok.Data;

@Data
public class BloggerOutputDto {
	
	private int userId;
	private String bloggerName;
	private int karma;
	private List<Post> post;
}
