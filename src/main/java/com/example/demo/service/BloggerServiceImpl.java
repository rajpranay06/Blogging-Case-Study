package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Award;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Comment;
import com.example.demo.bean.Community;
import com.example.demo.exception.AwardFoundException;
import com.example.demo.exception.BloggerIdNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IAwardRepository;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommentRepository;
import com.example.demo.bean.Post;
import com.example.demo.repository.IPostRepository;
import com.example.demo.exception.CommunityFoundException;
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
	
	@Autowired
	IAwardRepository awardRepo;

	@Override
	public Blogger addBlogger(Blogger blogger) {
		return blogRepo.save(blogger);
	}

	@Override
	public Blogger addBloggerDto(BloggerInputDto bloggerInputDto) {

		// Creating blogger object
		Blogger blog = new Blogger();

		// Setting blogger variables by bloggerInputDto values
		blog.setBloggerName(bloggerInputDto.getBloggerName());
		blog.setKarma(bloggerInputDto.getKarma());

		// Creating a list of comments
		List<Comment> comments = new ArrayList<>();

		// Getting comments from the Comment Entity by using ids
		for (Integer id : bloggerInputDto.getCommentIds()) {
			Comment comment = commentRepo.findById(id).get();
			comments.add(comment);
		}
		blog.setComments(comments);

		// Creating a list of posts
		List<Post> posts = new ArrayList<>();

		// Getting posts from the Post Entity by using ids
		for (Integer id : bloggerInputDto.getPostIds()) {
			posts.add(postRepo.findById(id).get());
		}

		blog.setPosts(posts);

		// List to store communities
		List<Community> communities = new ArrayList<>();

		// Getting community IDs
		List<Integer> communityIds = bloggerInputDto.getCommunityIds();
		if (!communityIds.isEmpty()) {
			for (Integer id : communityIds) {

				// Getting community by ID
				Optional<Community> opt = commRepo.findById(id);
				if (!opt.isPresent()) {
					throw new CommunityFoundException("Community is already present with the given id: " + id);
				}

				// Adding community to list
				communities.add(opt.get());
			}
			// Setting the communities to blog
			blog.setCommunities(communities);
		}
		
		// List to store awards
				List<Award> awards = new ArrayList<>();

				// Getting award IDs
				List<Integer> awardIds = bloggerInputDto.getAwardsIds();
				if (!awardIds.isEmpty()) {
					for (Integer id : awardIds) {

						// Getting award by ID
						Optional<Award> opt = awardRepo.findById(id);
						if (!opt.isPresent()) {
							throw new AwardFoundException("Award is already present with the given id: " + id);
						}

						// Adding award to list
						awards.add(opt.get());
					}
					// Setting the awards to blog
					blog.setAwardsReceived(awards);
				}

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
		for (Integer id : blogger.getCommentIds()) {
			comments.add(commentRepo.findById(id).get());
		}
		updateBlogger.setComments(comments);

		// List to store communities
		List<Community> communities = new ArrayList<>();

		List<Integer> communityIds = blogger.getCommunityIds();
		if (!communityIds.isEmpty()) {
			for (Integer id : communityIds) {
				Optional<Community> opt1 = commRepo.findById(id);
				if (!opt1.isPresent()) {
					throw new CommunityFoundException("Community is already present with the given id: " + id);
				}

				communities.add(opt1.get());
			}
		}
		updateBlogger.setCommunities(communities);

		// Creating a list of posts
		List<Post> posts = new ArrayList<>();

		// Getting posts from the Post Entity by using ids
		for (Integer id : blogger.getPostIds()) {
			posts.add(postRepo.findById(id).get());
		}

		updateBlogger.setPosts(posts);
		
		// List to store awards
				List<Award> awards = new ArrayList<>();

				List<Integer> awardIds = blogger.getAwardsIds();
				if (!awardIds.isEmpty()) {
					for (Integer id : awardIds) {
						Optional<Award> opt1 = awardRepo.findById(id);
						if (!opt1.isPresent()) {
							throw new AwardFoundException("Award is already present with the given id: " + id);
						}

						
						awards.add(opt1.get());
					}
				}
				updateBlogger.setAwardsReceived(awards);

		return blogRepo.save(updateBlogger);

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
		if (blog == null) {
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

	public List<BloggerOutputDto> viewBloggerListByCommunityId(int communityId) throws IdNotFoundException {

		List<Blogger> bloggers = blogRepo.viewBloggerListByCommunityId(communityId);
		if (bloggers.isEmpty()) {
			throw new IdNotFoundException("Bloggers not found with the community id:" + communityId);
		}
		List<BloggerOutputDto> allBloggers = new ArrayList<>();

		for (Blogger blogger : bloggers) {
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
	public BloggerOutputDto getBloggerByPostId(int postId) {
		Blogger blogger = blogRepo.getBloggerByPostId(postId);
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();

		bloggerOutputDto.setUserId(blogger.getUserId());
		bloggerOutputDto.setBloggerName(blogger.getBloggerName());
		bloggerOutputDto.setKarma(blogger.getKarma());

		return bloggerOutputDto;
	}

	@Override
	public List<BloggerOutputDto> getBloggerByAwardId(int awardId) throws IdNotFoundException {

		List<Blogger> bloggers = blogRepo.getBloggerByAwardId(awardId);
		if (bloggers.isEmpty()) {
			throw new IdNotFoundException("Bloggers not found with the Award id:" + awardId);
		}
		List<BloggerOutputDto> allBloggers = new ArrayList<>();

		for (Blogger blogger : bloggers) {
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
