package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BloggerDto;
import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.dto.CommentOutputDto;
import com.example.demo.dto.CommunityOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Comment;
import com.example.demo.bean.Community;
import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.exception.PostIdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.bean.Post;
import com.example.demo.repository.IPostRepository;
import com.example.demo.exception.CommunityNotFoundException;
import com.example.demo.repository.ICommunityRepository;

@Service
public class BloggerServiceImpl implements IBloggerService {

	@Autowired
	IBloggerRepository blogRepo;
	
	@Autowired
	ICommentRepository commentRepo;
	
	@Autowired
	IPostRepository postRepo;
	
	@Autowired
	ICommunityRepository commRepo;

	@Override
	public Blogger addBlogger(Blogger blogger) {
		return blogRepo.save(blogger);
	}

	@Override
	public BloggerDto addBloggerDto(BloggerInputDto bloggerInputDto) {
		
		// Creating blogger object
		Blogger blog = new Blogger();
		
		//Setting blogger variables by bloggerInputDto values
		blog.setBloggerName(bloggerInputDto.getBloggerName());
		
		// Creating a list of comments
		List<Comment> comments = new ArrayList<>();
		
		// Getting comments from the Comment Entity by using ids
		for(Integer id : bloggerInputDto.getCommentIds()) {
			Optional<Comment> opt = commentRepo.findById(id);
			if(!opt.isPresent()) {
				throw new CommentNotFoundException("No comment found with id: " + id);
			}
			comments.add(opt.get());
		}
		blog.setComments(comments);

		// Creating a list of posts
		List<Post> posts = new ArrayList<>();
						
		// Getting posts from the Post Entity by using ids
		for(Integer id : bloggerInputDto.getPostIds()) {
			Optional<Post> opt = postRepo.findById(id);
			if(!opt.isPresent()) {
				throw new PostIdNotFoundException("No comment found with id: " + id);
			}
			posts.add(opt.get());
		}
						
		blog.setPosts(posts);	

		// List to store communities
		List<Community> communities = new ArrayList<>();
		
		// Getting community IDs
		List<Integer> communityIds = bloggerInputDto.getCommunityIds();
		if(!communityIds.isEmpty()) {
			for(Integer id : communityIds) {
				
				// Getting community by ID
				Optional<Community> opt = commRepo.findById(id);
				if(!opt.isPresent()) {
					throw new CommunityNotFoundException("No community is for with given id: "+ id);				}
				
				// Adding community to list
				communities.add(opt.get());
			}
			// Setting the communities to blog
			blog.setCommunities(communities);
		}
		
		// Updating karma points
		blog.setKarma(bloggerInputDto.getPostIds().size() * 50);
		
		// Saving the blogger in database
		Blogger newBlogger = blogRepo.save(blog);	
		
		BloggerDto bloggerDto = new BloggerDto();
		bloggerDto.setUserId(newBlogger.getUserId());
		bloggerDto.setBloggerName(newBlogger.getBloggerName());
		bloggerDto.setKarma(newBlogger.getKarma());
		bloggerDto.setPosts(newBlogger.getPosts());
		
		List<CommunityOutputDto> newCommunities = new ArrayList<>();
		
		for(Community community : newBlogger.getCommunities()) {
			
			//Creating communityOutputDto object
			CommunityOutputDto com = new CommunityOutputDto();
			
			//Set values to community object
			com.setCommunityId(community.getCommunityId());
			com.setCommunityDescription(community.getCommunityDescription());
			com.setTotalMembers(community.getTotalMembers());
			com.setOnlineMembers(community.getOnlineMembers());
			com.setImage(community.getImage());
			com.setCreatedOn(community.getCreatedOn());
			com.setPostRulesAllowed(community.getPostRulesAllowed());
			com.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
			com.setBanningPolicy(community.getBanningPolicy());
			com.setFlairs(community.getFlairs());
			
			// Adding com to communities
			newCommunities.add(com);
		}
		
		bloggerDto.setCommunities(newCommunities);
		
		List<CommentOutputDto> newComments = new ArrayList<>();
		
		for(Comment comment : newBlogger.getComments()) {
			
			CommentOutputDto com = new CommentOutputDto();
			
			com.setCommentId(comment.getCommentId());
			com.setCommentDescription(comment.getCommentDescription());
			com.setVotes(comment.getVotes());
			com.setVoteUp(comment.isVoteUp());
			
			newComments.add(com);
		}
		
		bloggerDto.setComments(newComments);
		
		return bloggerDto;
		
	}

	@Override
	public BloggerDto updateBlogger(BloggerInputDto blogger) throws IdNotFoundException {
		
		Optional<Blogger> opt1 = blogRepo.findById(blogger.getUserId());
		if (!opt1.isPresent()) {
			throw new IdNotFoundException("Blogger not found with the given id:" + blogger.getUserId());
		}
		Blogger updateBlogger = opt1.get();
		
		// Setting values to updateBlogger
		updateBlogger.setBloggerName(blogger.getBloggerName());
		
		// Creating a list of comments
		List<Comment> comments = new ArrayList<>();
		// Getting comments from the Comment Entity by using ids
		for(Integer id : blogger.getCommentIds() ) {
			Optional<Comment> opt = commentRepo.findById(id);
			if(!opt.isPresent()) {
				throw new CommentNotFoundException("No comment found with id: " + id);
			}
			comments.add(opt.get());
		}
		updateBlogger.setComments(comments);

		// List to store communities
		List<Community> communities = new ArrayList<>();
		
		List<Integer> communityIds = blogger.getCommunityIds();
		if(!communityIds.isEmpty()) {
			for(Integer id : communityIds) {
				Optional<Community> opt = commRepo.findById(id);
				if(opt.isPresent()) {
					communities.add(opt.get());
				}
				else {
					throw new CommunityNotFoundException("No community is for with given id: "+ id);
				}
			}
		}
		updateBlogger.setCommunities(communities);
		
		// Creating a list of posts
		List<Post> posts = new ArrayList<>();
						
		// Getting posts from the Post Entity by using ids
		for(Integer id : blogger.getPostIds()) {
			Optional<Post> opt = postRepo.findById(id);
			if(opt.isPresent()) {
				posts.add(opt.get());
			}
			else {
				throw new PostIdNotFoundException("No comment found with id: " + id);
			}	
		}
						
		updateBlogger.setPosts(posts);
		
		// Updating karma points
		updateBlogger.setKarma(blogger.getPostIds().size() * 50);
		
		Blogger newBlogger = blogRepo.save(updateBlogger);
		
		BloggerDto bloggerDto = new BloggerDto();
		bloggerDto.setUserId(newBlogger.getUserId());
		bloggerDto.setBloggerName(newBlogger.getBloggerName());
		bloggerDto.setKarma(newBlogger.getKarma());
		bloggerDto.setPosts(newBlogger.getPosts());
		
		List<CommunityOutputDto> newCommunities = new ArrayList<>();
		
		for(Community community : newBlogger.getCommunities()) {
			
			//Creating communityOutputDto object
			CommunityOutputDto com = new CommunityOutputDto();
			
			//Set values to community object
			com.setCommunityId(community.getCommunityId());
			com.setCommunityDescription(community.getCommunityDescription());
			com.setTotalMembers(community.getTotalMembers());
			com.setOnlineMembers(community.getOnlineMembers());
			com.setImage(community.getImage());
			com.setCreatedOn(community.getCreatedOn());
			com.setPostRulesAllowed(community.getPostRulesAllowed());
			com.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
			com.setBanningPolicy(community.getBanningPolicy());
			com.setFlairs(community.getFlairs());
			
			// Adding com to communities
			newCommunities.add(com);
		}
		
		bloggerDto.setCommunities(newCommunities);
		
		List<CommentOutputDto> newComments = new ArrayList<>();
		
		for(Comment comment : newBlogger.getComments()) {
			
			CommentOutputDto com = new CommentOutputDto();
			
			com.setCommentId(comment.getCommentId());
			com.setCommentDescription(comment.getCommentDescription());
			com.setVotes(comment.getVotes());
			com.setVoteUp(comment.isVoteUp());
			
			newComments.add(com);
		}
		
		bloggerDto.setComments(newComments);
		
		return bloggerDto;

	}

	@Override
	public void deleteBlogger(int bloggerId) throws IdNotFoundException {
		
		Optional<Blogger> opt = blogRepo.findById(bloggerId);
		if (!opt.isPresent()) {
			throw new IdNotFoundException("Blogger not found with the given id:" + bloggerId);
		}
		
		Blogger blogger = opt.get();
		blogRepo.delete(blogger);
	}
	

	@Override
	public BloggerOutputDto viewBlogger(int bloggerId) throws IdNotFoundException {

		Optional<Blogger> opt = blogRepo.findById(bloggerId);
		if (!opt.isPresent()) {
			throw new IdNotFoundException("Blogger not found with the given id:" + bloggerId);
		}

		Blogger blogger = opt.get();
		
		BloggerOutputDto blog = new BloggerOutputDto();
		
		blog.setBloggerName(blogger.getBloggerName());
		blog.setUserId(blogger.getUserId());
		blog.setKarma(blogger.getKarma());
		
		return blog;
	}

	@Override
	public List<BloggerOutputDto> viewAllBloggers() {
		List<BloggerOutputDto> bloggers = new ArrayList<>();
		
		for(Blogger blogger : blogRepo.findAll()) {
			BloggerOutputDto blog = new BloggerOutputDto();
			
			blog.setBloggerName(blogger.getBloggerName());
			blog.setUserId(blogger.getUserId());
			blog.setKarma(blogger.getKarma());
			
			bloggers.add(blog);
		}
		
		return bloggers;
	}

	@Override
	public BloggerOutputDto getBloggerByCommentId(int commentId) throws IdNotFoundException {
		
		Blogger blog = blogRepo.getBloggerByCommentId(commentId);
		if(blog == null) {
			throw new IdNotFoundException("No blogger found with comment id: " + commentId);
		}

		// Creating PostOutputDto object
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
					
		// Setting values for bloggerOutputDto by deletedBlogger values
		bloggerOutputDto.setUserId(blog.getUserId());
		bloggerOutputDto.setBloggerName(blog.getBloggerName());
		bloggerOutputDto.setKarma(blog.getKarma());
		
		return bloggerOutputDto;
	}
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

	@Override
	public BloggerOutputDto getBloggerByPostId(int postId) throws IdNotFoundException {
		Blogger blogger = blogRepo.getBloggerByPostId(postId);
		if(blogger == null) {
			throw new IdNotFoundException("Bloggers not found with the post id:" + postId);
		}
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
		
		bloggerOutputDto.setUserId(blogger.getUserId());
		bloggerOutputDto.setBloggerName(blogger.getBloggerName());
		bloggerOutputDto.setKarma(blogger.getKarma());
		
		return bloggerOutputDto;
	}
	


	



}
