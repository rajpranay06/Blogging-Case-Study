package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BloggerDto;
import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.exception.IdNotFoundException;

public interface IBloggerService {
	
	public Blogger addBlogger(Blogger blogger);
	public BloggerDto updateBlogger(BloggerInputDto blogger) throws IdNotFoundException;
	public void deleteBlogger(int bloggerId) throws IdNotFoundException;
	public BloggerOutputDto viewBlogger(int bloggerId) throws IdNotFoundException;
	public List<BloggerOutputDto> viewAllBloggers();
	public BloggerOutputDto getBloggerByCommentId(int commentId);
	public List<BloggerOutputDto> viewBloggerListByCommunityId(int communityId) throws IdNotFoundException;
	public BloggerDto addBloggerDto(BloggerInputDto blogger);
	public BloggerOutputDto getBloggerByPostId(int postId);

}
