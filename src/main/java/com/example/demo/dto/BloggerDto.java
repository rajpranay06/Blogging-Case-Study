package com.example.demo.dto;

import java.util.List;

import com.example.demo.bean.Post;

import lombok.Data;

@Data
public class BloggerDto {

	private int userId;
	private String bloggerName;
	private int karma;
	private List<Post> posts;
	private List<CommunityOutputDto> communities;
	private List<CommentOutputDto> comments;
}
