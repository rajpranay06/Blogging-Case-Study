package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Post;
import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;

public interface IPostService {
	
	public PostDto addPost(PostInputDto post);
	public PostDto updatePost(PostInputDto post);
	public void deletePost(int id);
	public List<PostOutputDto> getPostBySearchString(String searchStr);
	public void upVote(int postId, boolean upVote);
	public Post addPostWithoutDto(Post post);
	public List<PostOutputDto> getPostByawardId(int awardId);
	public List<PostOutputDto> listPostsByCommunityId(int communityId);
	public PostOutputDto getPostByCommentId(int commentId);
	public List<PostOutputDto> getPostsByBlogger(int bloggerId);
	public List<PostOutputDto> getUpvotedPostsOfBlogger(int bloggerId);
}
