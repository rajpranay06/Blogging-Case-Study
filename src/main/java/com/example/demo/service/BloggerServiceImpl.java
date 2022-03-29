package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Comment;
import com.example.demo.bean.Community;
import com.example.demo.exception.BloggerIdNotFoundException;
import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommentRepository;

@Service
public class BloggerServiceImpl implements IBloggerService {

	@Autowired
	IBloggerRepository blogRepo;
	
	@Autowired
	ICommentRepository commentRepo;

	@Override
	public Blogger addBlogger(Blogger blogger) {
		return blogRepo.save(blogger);
	}

	@Override
	public Blogger addBloggerDto(BloggerInputDto bloggerInputDto) {
		// Creating blogger object
		Blogger blog = new Blogger();
		
		//Setting blogger variables by bloggerInputDto values
		blog.setBloggerName(bloggerInputDto.getBloggerName());
		blog.setKarma(bloggerInputDto.getKarma());
		
		// Creating a list of comments
		List<Comment> comments = new ArrayList<>();
		
		// Getting comments from the Comment Entity by using ids
		for(Integer id : bloggerInputDto.getCommentIds()) {
			Comment comment = commentRepo.findById(id).get();
			comments.add(comment);
		}
		blog.setComments(comments);
				
		// Saving the blogger in database
		return blogRepo.save(blog);
	}

	@Override
	public Blogger updateBlogger(BloggerInputDto blogger) throws IdNotFoundException {
		Optional<Blogger> opt = blogRepo.findById(blogger.getUserId());
		if (!opt.isPresent()) {
			throw new IdNotFoundException("Blogger not found with the given id:" + blogger.getUserId());
		}
		Blogger updateBlogger = opt.get();
		
		// Setting values to updateBlogger
		updateBlogger.setBloggerName(blogger.getBloggerName());
		updateBlogger.setKarma(blogger.getKarma());
		
		// Creating a list of comments
		List<Comment> comments = new ArrayList<>();
		// Getting comments from the Comment Entity by using ids
		for(Integer id : blogger.getCommentIds() ) {
			comments.add(commentRepo.findById(id).get());
		}
		updateBlogger.setComments(comments);
		
		return blogRepo.save(updateBlogger);

	}

	@Override
	public Blogger deleteBlogger(BloggerInputDto bloggerInput) throws IdNotFoundException {
		Optional<Blogger> opt = blogRepo.findById(bloggerInput.getUserId());
		if (!opt.isPresent()) {
			throw new IdNotFoundException("Blogger not found with the given id:" + bloggerInput.getUserId());
		}
		blogRepo.deleteById(bloggerInput.getUserId());
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
	public BloggerOutputDto getBloggerByCommentId(int commentId) {
		Blogger blog = blogRepo.getBloggerByCommentId(commentId);
		if(blog == null) {
			throw new BloggerIdNotFoundException("No post found with comment id: " + commentId);
		}

		// Creating PostOutputDto object
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
					
		// Setting values for bloggerOutputDto by deletedBlogger values
		bloggerOutputDto.setUserId(blog.getUserId());
		bloggerOutputDto.setBloggerName(blog.getBloggerName());
		bloggerOutputDto.setKarma(blog.getKarma());
		
		return bloggerOutputDto;
	}



}
