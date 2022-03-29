package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Comment;
import com.example.demo.bean.Community;
import com.example.demo.bean.Post;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.IPostRepository;
import com.example.demo.exception.CommunityFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommunityRepository;

@Service
public class BloggerServiceImpl implements IBloggerService {

	@Autowired
	IBloggerRepository blogRepo;
	
	@Autowired
	IPostRepository postRepo;
	ICommunityRepository commRepo;

	@Override
	public Blogger addBlogger(Blogger blogger) {
		return blogRepo.save(blogger);
	}

	@Override
	public Blogger addBloggerDto(BloggerInputDto bloggerInputDto) {

		// Creating blogger object
		Blogger blog = new Blogger();
		blog.setBloggerName(bloggerInputDto.getBloggerName());
		blog.setKarma(bloggerInputDto.getKarma());
		
		// Creating a list of posts
		List<Post> posts = new ArrayList<>();
						
		// Getting posts from the Post Entity by using ids
		for(Integer id : bloggerInputDto.getPostIds()) {
			posts.add(postRepo.findById(id).get());
		}
						
		blog.setPosts(posts);	
		
		Blogger newBlog = blogRepo.save(blog);
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
		bloggerOutputDto.setUserId(newBlog.getUserId());
		bloggerOutputDto.setBloggerName(newBlog.getBloggerName());
		bloggerOutputDto.setKarma(newBlog.getKarma());
		bloggerOutputDto.setPost(newBlog.getPosts());
		return bloggerOutputDto;
		// List to store communities
		List<Community> communities = new ArrayList<>();
		
		List<Integer> communityIds = bloggerInputDto.getCommunityIds();
		if(!communityIds.isEmpty()) {
			for(Integer id : communityIds) {
				Optional<Community> opt = commRepo.findById(id);
				if(!opt.isPresent()) {
					throw new CommunityFoundException("Community is already present with the given id: "+ id);
				}
				
				communities.add(opt.get());
			}
			blog.setCommunities(communities);
		}
		
		// saving blogger in database
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

		// List to store communities
		List<Community> communities = new ArrayList<>();
		
		List<Integer> communityIds = blogger.getCommunityIds();
		if(!communityIds.isEmpty()) {
			for(Integer id : communityIds) {
				Optional<Community> opt1 = commRepo.findById(id);
				if(!opt1.isPresent()) {
					throw new CommunityFoundException("Community is already present with the given id: "+ id);
				}
				
				communities.add(opt1.get());
			}
		}
		updateBlogger.setCommunities(communities);
		
		return blogRepo.save(updateBlogger);

	}

	@Override
	public Blogger deleteBlogger(BloggerInputDto blogger) throws IdNotFoundException {
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
	public List<BloggerOutputDto> viewBloggerListByCommunityId(int communityId) throws IdNotFoundException {
		List<Blogger> bloggers = blogRepo.viewBloggerListByCommunityId(communityId);
		if(bloggers.isEmpty()) {
			throw new IdNotFoundException("Bloggers not found with the community id:" + communityId);
		}
		List<BloggerOutputDto> allBloggers = new ArrayList<>();
		
		for(Blogger blogger : bloggers) {
			// Creating bloggerOutputDto and setting values
			BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
			
			bloggerOutputDto.setUserId(blogger.getUserId());
			bloggerOutputDto.setBloggerName(blogger.getBloggerName());
			bloggerOutputDto.setKarma(blogger.getKarma());
			
			allBloggers.add(bloggerOutputDto);
		}
		
		return allBloggers;
	}
	


	

}
