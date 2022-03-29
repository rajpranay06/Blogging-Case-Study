package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Blogger;

import com.example.demo.bean.Award;
import com.example.demo.bean.Post;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;

public interface IPostService {
	public PostOutputDto addPost(PostInputDto post);
	public PostOutputDto updatePost(PostInputDto post);
	public PostOutputDto deletePost(int id);
	public List<PostOutputDto> getPostBySearchString(String searchStr);
	//public List<PostOutputDto> getPostsByBlogger(int bloggerId);
	public void upVote(int postId, boolean upVote);
	public Post addPostWithoutDto(Post post);
	public List<PostOutputDto> getPostsByBlogger(int id);
	public Post addPost(PostInputDto post);
	public Post updatePost(PostInputDto post);
	public Post deletePost(int id);
	public List<Post> getPostBySearchString(String searchStr);
//	public List<PostOutputDto> getPostByBlogger(Blogger blogger);
	public void upVote(int postId, boolean upVote);
	public Post addPostWithoutDto(Post post);
	List<PostOutputDto> getPostByawardId(int id);
	public List<Post> listPostsByCommunityId(int communityId);

	public PostOutputDto getPostByCommentId(int commentId);
}
