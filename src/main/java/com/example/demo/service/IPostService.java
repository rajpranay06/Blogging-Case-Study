package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Post;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;

public interface IPostService {
	public PostOutputDto addPost(PostInputDto post);
	public PostOutputDto updatePost(PostInputDto post);
	public PostOutputDto deletePost(int id);
	public List<PostOutputDto> getPostBySearchString(String searchStr);
//	public List<PostOutputDto> getPostByBlogger(Blogger blogger);
	public void upVote(int postId, boolean upVote);
	public Post addPostWithoutDto(Post post);
	public List<Post> listPostsByCommunityId(int communityId);
}
