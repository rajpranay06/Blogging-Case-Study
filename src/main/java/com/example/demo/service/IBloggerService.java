package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Community;
import com.example.demo.exception.IdNotFoundException;

public interface IBloggerService {
	public Blogger addBlogger(Blogger blogger);

	public Blogger updateBlogger(Blogger blogger) throws IdNotFoundException;

	public Blogger deleteBlogger(Blogger blogger) throws IdNotFoundException;

	public Blogger viewBlogger(int bloggerId) throws IdNotFoundException;

	public List<Blogger> viewAllBloggers();

	public List<Blogger> viewBloggerList(Community community);

	// public List<Customer> viewCustomerList(int theatreid);
	public BloggerOutputDto addBloggerDto(BloggerInputDto blogger);

}
