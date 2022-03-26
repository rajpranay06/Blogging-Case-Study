package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Community;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IBloggerRepository;

@Service
public class BloggerServiceImpl implements IBloggerService {

	@Autowired
	IBloggerRepository blogRepo;

	@Override
	public Blogger addBlogger(Blogger blogger) {
		return blogRepo.save(blogger);
	}

	@Override
	public BloggerOutputDto addBloggerDto(BloggerInputDto bloggerInputDto) {

		Blogger blog = new Blogger();
		blog.setBloggerName(bloggerInputDto.getBloggerName());
		blog.setKarma(bloggerInputDto.getKarma());
		Blogger newBlog = blogRepo.save(blog);
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
		bloggerOutputDto.setUserId(newBlog.getUserId());
		bloggerOutputDto.setBloggerName(newBlog.getBloggerName());
		bloggerOutputDto.setKarma(newBlog.getKarma());
		return bloggerOutputDto;
	}

	@Override
	public Blogger updateBlogger(Blogger blogger) throws IdNotFoundException {
		Optional<Blogger> opt = blogRepo.findById(blogger.getUserId());
		if (!opt.isPresent()) {
			throw new IdNotFoundException("Blogger not found with the given id:" + blogger.getUserId());
		}
		return blogRepo.save(blogger);

	}

	@Override
	public Blogger deleteBlogger(Blogger blogger) throws IdNotFoundException {
		Optional<Blogger> opt = blogRepo.findById(blogger.getUserId());
		if (!opt.isPresent()) {
			throw new IdNotFoundException("Blogger not found with the given id:" + blogger.getUserId());
		}
		blogRepo.deleteById(blogger.getUserId());
		return opt.get();
	}

	@Override
	public Blogger viewBlogger(int bloggerId) throws IdNotFoundException {

		Optional<Blogger> opt = blogRepo.findById(bloggerId);
		if (!opt.isPresent()) {
			throw new IdNotFoundException("Blogger not found with the given id:" + bloggerId);
		}

		return opt.get();
	}

	@Override
	public List<Blogger> viewAllBloggers() {
		return blogRepo.findAll();
	}

	@Override
	public List<Blogger> viewBloggerList(Community community) {
		
		return null;
	}

}
