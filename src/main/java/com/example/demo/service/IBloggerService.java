package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.exception.IdNotFoundException;

public interface IBloggerService {
	public Blogger addBlogger(Blogger blogger);

	public Blogger updateBlogger(BloggerInputDto blogger) throws IdNotFoundException;

	public Blogger deleteBlogger(BloggerInputDto blogger) throws IdNotFoundException;

	public Blogger viewBlogger(int bloggerId) throws IdNotFoundException;

	public List<Blogger> viewAllBloggers();

	public List<BloggerOutputDto> viewBloggerListByCommunityId(int communityId) throws IdNotFoundException;

	// public List<Customer> viewCustomerList(int theatreid);
	public BloggerOutputDto addBloggerDto(BloggerInputDto blogger);
	
	public Blogger addBloggerDto(BloggerInputDto blogger);

}
