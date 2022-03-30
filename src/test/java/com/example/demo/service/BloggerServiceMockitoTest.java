package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Comment;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommentRepository;

import org.mockito.BDDMockito;

import com.example.demo.dto.CommunityInputDto;
import com.example.demo.bean.Community;
import com.example.demo.bean.Post;
import com.example.demo.bean.PostType;
import com.example.demo.repository.ICommunityRepository;
import com.example.demo.repository.IPostRepository;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class BloggerServiceMockitoTest {
	
	// @InjectMocks - Creates instance of a class and injects mocks that are created with @Mock
	@InjectMocks
	BloggerServiceImpl blogSer;

	// @MockBean - Creates Mock of a class or interface
	@MockBean
	IBloggerRepository blogRepo;
	
	@MockBean
	ICommunityRepository comRepo;
	
	@MockBean
	IPostRepository postRepo;

	@MockBean
	ICommentRepository commentRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addBlogger() {
		// Creating blogger object
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setUserId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		Comment comment1 = new Comment();
		comment1.setCommentId(26);
		comment1.setCommentDescription("Awesome");
		comment1.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(commentRepo.findById(26)).thenReturn(Optional.of(comment1));
		
		Comment comment2 = new Comment();
		comment2.setCommentId(27);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(commentRepo.findById(27)).thenReturn(Optional.of(comment2));
		
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);
		
		blogger.setComments(comments);
		
		// Post
		Post newPost = new Post();
		// Setting the values
		newPost.setPostId(100);
		newPost.setTitle("Lucifer");
		newPost.setContent(PostType.VIDEO_IMAGE);
		newPost.setCreatedDateTime(LocalDateTime.now());
		newPost.setFlair("Deckerstar");
		newPost.setNotSafeForWork(false);
		newPost.setOriginalContent(true);
		newPost.setVotes(10000);
		newPost.setVoteUp(false);
		newPost.setSpoiler(true);
		
		Comment comment_1 = new Comment();
		comment_1.setCommentId(26);
		comment_1.setCommentDescription("Awesome");
		comment_1.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(commentRepo.findById(26)).thenReturn(Optional.of(comment_1));
		
		Comment comment_2 = new Comment();
		comment_2.setCommentId(27);
		comment_2.setCommentDescription("Fab");
		comment_2.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(commentRepo.findById(27)).thenReturn(Optional.of(comment_2));
		
		List<Comment> postComments = new ArrayList<>();
		comments.add(comment_1);
		comments.add(comment_2);
		
		newPost.setComments(postComments);
		
		List<Post> posts = new ArrayList<>();
		posts.add(newPost);
		
		blogger.setPosts(posts);
		
		// Community 
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("SportsNews");
		
		List<Integer> p = new ArrayList<Integer>();
		List<Post> communityPosts = new ArrayList<Post>();
		
		Post post1 = new Post();
		post1.setPostId(100);
		post1.setTitle("Lucifer");
		post1.setContent(PostType.VIDEO_IMAGE);
		post1.setCreatedDateTime(LocalDateTime.now());
		post1.setFlair("Deckerstar");
		post1.setNotSafeForWork(false);
		post1.setOriginalContent(true);
		post1.setVotes(10000);
		post1.setVoteUp(false);
		post1.setSpoiler(true);
		
		Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post1));
		communityPosts.add(post1);
		p.add(post1.getPostId());
		
		CommunityInputDto com = new CommunityInputDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,p);
		Community newCommunity = new Community();
		newCommunity.setCommunityId(com.getCommunityId());
		newCommunity.setCommunityDescription(com.getCommunityDescription());
		newCommunity.setTotalMembers(com.getTotalMembers());
		newCommunity.setOnlineMembers(com.getOnlineMembers());
		newCommunity.setImage(com.getImage());
		newCommunity.setCreatedOn(com.getCreatedOn());
		newCommunity.setPostRulesAllowed(com.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(com.getBanningPolicy());
		newCommunity.setFlairs(com.getFlairs());
		newCommunity.setPost(posts);
		
		List<Community> communities = new ArrayList<>();
		communities.add(newCommunity);
		
		blogger.setCommunities(communities);
		
		// Sending post object when save function is called
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);
		
		Blogger newBlog = blogSer.addBlogger(blogger);

		// checking if the added blogger values are equal to the newBlog or not
		assertEquals(1, newBlog.getUserId());
		assertEquals("Abc", newBlog.getBloggerName());
		assertEquals(20,newBlog.getKarma());
		assertEquals(2, newBlog.getComments().size());
		assertEquals(1, newBlog.getPosts().size());
		assertEquals(1, newBlog.getCommunities().size());
	}
	
	@Test
	void addBloggerDtoTest() {
		// Creating BloggerInputDto object
		BloggerInputDto bloggerInput = new BloggerInputDto();
		
		// Setting the values
		bloggerInput.setUserId(140);
		bloggerInput.setBloggerName("Abcde");
		bloggerInput.setKarma(24);
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(19);
		commentIds.add(20);
		
		bloggerInput.setCommentIds(commentIds);
		
		// Creating blogger object
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setKarma(bloggerInput.getKarma());
		
		Comment comment1 = new Comment();
		comment1.setCommentId(26);
		comment1.setCommentDescription("Awesome");
		comment1.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(commentRepo.findById(19)).thenReturn(Optional.of(comment1));
		
		Comment comment2 = new Comment();
		comment2.setCommentId(27);
		comment2.setCommentDescription("Fab");
		comment2.setVotes(10);
		
		// Sending comment when getCommentById is called
		Mockito.when(commentRepo.findById(20)).thenReturn(Optional.of(comment2));

		// List to store comments 
		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);

		blogger.setComments(comments);
		System.out.println(blogger);
		
		// Sending blogger object when save function is called
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);
		// List to store community 
		List<Community> communities = new ArrayList<>();
		
		for(Integer id : bloggerInput.getCommunityIds()) {
			File fw = new File("abc.jpg");
			
			List<String> glist = new ArrayList<String>();
			glist.add("Hockey");
			glist.add("Cricket");
			glist.add("Tennis");
			
			List<String> galist = new ArrayList<String>();
			galist.add("Tours");
			galist.add("Furniture");
			galist.add("Houses");
			
			List<String> bp = new ArrayList<String>();
			bp.add("Cheating");
			bp.add("Drugs");
			bp.add("Misuse");
			
			List<String> f = new ArrayList<String>();
			f.add("SportsNews");
			
			List<Integer> p = new ArrayList<Integer>();
			List<Post> posts = new ArrayList<Post>();
			
			Post post1 = new Post();
			post1.setPostId(100);
			post1.setTitle("Lucifer");
			post1.setContent(PostType.VIDEO_IMAGE);
			post1.setCreatedDateTime(LocalDateTime.now());
			post1.setFlair("Deckerstar");
			post1.setNotSafeForWork(false);
			post1.setOriginalContent(true);
			post1.setVotes(10000);
			post1.setVoteUp(false);
			post1.setSpoiler(true);
			
			Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post1));
			posts.add(post1);
			p.add(post1.getPostId());
			
			// Creating communityDto object using constructor
			CommunityInputDto com = new CommunityInputDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,p);
			
			// Creating community object and setting values
			Community newCommunity = new Community();
			newCommunity.setCommunityId(com.getCommunityId());
			newCommunity.setCommunityDescription(com.getCommunityDescription());
			newCommunity.setTotalMembers(com.getTotalMembers());
			newCommunity.setOnlineMembers(com.getOnlineMembers());
			newCommunity.setImage(com.getImage());
			newCommunity.setCreatedOn(com.getCreatedOn());
			newCommunity.setPostRulesAllowed(com.getPostRulesAllowed());
			newCommunity.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
			newCommunity.setBanningPolicy(com.getBanningPolicy());
			newCommunity.setFlairs(com.getFlairs());
			
			// adding new communities top community list
			communities.add(newCommunity);
			
			// Returning newCommunity when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newCommunity));
			
		}
		blogger.setCommunities(communities);
		
		// Returning blogger when save is called
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);
		
		Blogger blogOutput = blogSer.addBloggerDto(bloggerInput);
		
		assertEquals("Kevin", blogOutput.getBloggerName());
		assertEquals(50, blogOutput.getKarma());
		assertEquals(1, blogOutput.getCommunities().size());
	}

	@Test
	void viewBloggerTest() throws IdNotFoundException {
		// Storing community ids
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(61);
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(18);
		List<Integer> postIds = new ArrayList<>();
		postIds.add(24);
		BloggerInputDto bloggerInput = new BloggerInputDto(89,"Mockadd", 4, commentIds, postIds, communityIds);

		Blogger blogger = new Blogger();
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setKarma(bloggerInput.getKarma());
		
		// List to store community 
		List<Community> communities = new ArrayList<>();
		
		for(Integer id : bloggerInput.getCommunityIds()) {
			File fw = new File("abc.jpg");
			
			List<String> glist = new ArrayList<String>();
			glist.add("Hockey");
			glist.add("Cricket");
			glist.add("Tennis");
			
			List<String> galist = new ArrayList<String>();
			galist.add("Tours");
			galist.add("Furniture");
			galist.add("Houses");
			
			List<String> bp = new ArrayList<String>();
			bp.add("Cheating");
			bp.add("Drugs");
			bp.add("Misuse");
			
			List<String> f = new ArrayList<String>();
			f.add("SportsNews");
			
			List<Integer> p = new ArrayList<Integer>();
			List<Post> posts = new ArrayList<Post>();
			
			Post post1 = new Post();
			post1.setPostId(100);
			post1.setTitle("Lucifer");
			post1.setContent(PostType.VIDEO_IMAGE);
			post1.setCreatedDateTime(LocalDateTime.now());
			post1.setFlair("Deckerstar");
			post1.setNotSafeForWork(false);
			post1.setOriginalContent(true);
			post1.setVotes(10000);
			post1.setVoteUp(false);
			post1.setSpoiler(true);
			
			Mockito.when(postRepo.findById(100)).thenReturn(Optional.of(post1));
			posts.add(post1);
			p.add(post1.getPostId());
			
			
			// Creating communityDto object using constructor
			CommunityInputDto com = new CommunityInputDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,p);
			
			// Creating community object and setting values
			Community newCommunity = new Community();
			newCommunity.setCommunityId(com.getCommunityId());
			newCommunity.setCommunityDescription(com.getCommunityDescription());
			newCommunity.setTotalMembers(com.getTotalMembers());
			newCommunity.setOnlineMembers(com.getOnlineMembers());
			newCommunity.setImage(com.getImage());
			newCommunity.setCreatedOn(com.getCreatedOn());
			newCommunity.setPostRulesAllowed(com.getPostRulesAllowed());
			newCommunity.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
			newCommunity.setBanningPolicy(com.getBanningPolicy());
			newCommunity.setFlairs(com.getFlairs());
			
			// adding new communities top community list
			communities.add(newCommunity);
			
			// Returning newCommunity when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newCommunity));
			
		}
		blogger.setCommunities(communities);
		
		Comment comment = new Comment();
		comment.setCommentId(18);
		comment.setCommentDescription("Good");
		comment.setVotes(1000);
		
		List<Comment> comments = new ArrayList<>();
		comments.add(comment);
		
		blogger.setComments(comments);
		
		List<Post> posts = new ArrayList<>();
		Post post1 = new Post();
		post1.setPostId(24);
		post1.setTitle("Lucifer");
		post1.setContent(PostType.VIDEO_IMAGE);
		post1.setCreatedDateTime(LocalDateTime.now());
		post1.setFlair("Deckerstar");
		post1.setNotSafeForWork(false);
		post1.setOriginalContent(true);
		post1.setVotes(10000);
		post1.setVoteUp(false);
		post1.setSpoiler(true);
		
		Mockito.when(postRepo.findById(24)).thenReturn(Optional.of(post1));
		posts.add(post1);
		
		blogger.setPosts(posts);

		Mockito.when(blogRepo.findById(89)).thenReturn(Optional.of(blogger));

		Blogger blog = blogSer.viewBlogger(89);
		
		assertEquals(89, blog.getUserId());
		assertEquals("Mockadd", blog.getBloggerName());
		assertEquals(4, blog.getKarma());
		assertEquals(1, blog.getCommunities().size());
		assertEquals(1, blog.getPosts().size());
		assertEquals(1, blog.getComments().size());
	}

	@Test
	public void viewAllBloggers() {
		List<Blogger> bloggers = new ArrayList<>();
		bloggers.add(new Blogger());

		BDDMockito.given(blogRepo.findAll()).willReturn(bloggers);

		List<Blogger> expected = blogSer.viewAllBloggers();

		assertEquals(expected, bloggers);
		verify(blogRepo).findAll();
	}

	@Test
	public void deleteBloggerTest() throws IdNotFoundException {
		
		BloggerInputDto bloggerInput = new BloggerInputDto();
		
		// Setting the values
		bloggerInput.setUserId(100);
		bloggerInput.setBloggerName("Abc");
		bloggerInput.setKarma(20);
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(16);
		
		bloggerInput.setCommentIds(commentIds);
		
		Blogger blogger = new Blogger();
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setKarma(bloggerInput.getKarma());
		
		// List to store comments 
		List<Comment> comments = new ArrayList<>();
		for(Integer id : bloggerInput.getCommentIds()) {
			
			// Creating comment object and setting values
			Comment newComment = new Comment();
			newComment.setCommentId(26);
			newComment.setCommentDescription("Awesome");
			newComment.setVotes(10);
			
			// adding new comments top comment list
		    comments.add(newComment);
		    
			// Returning newComment when findById is called
			Mockito.when(commentRepo.findById(id)).thenReturn(Optional.of(newComment));
		}
		
		blogger.setComments(comments);
		
		// Storing community ids
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(61);
		
		// List to store community 
		List<Community> communities = new ArrayList<>();
		
		for(Integer id : bloggerInput.getCommunityIds()) {
			File fw = new File("abc.jpg");
			
			List<String> glist = new ArrayList<String>();
			glist.add("Hockey");
			glist.add("Cricket");
			glist.add("Tennis");
			
			List<String> galist = new ArrayList<String>();
			galist.add("Tours");
			galist.add("Furniture");
			galist.add("Houses");
			
			List<String> bp = new ArrayList<String>();
			bp.add("Cheating");
			bp.add("Drugs");
			bp.add("Misuse");
			
			List<String> f = new ArrayList<String>();
			f.add("SportsNews");
			
			List<Integer> p = new ArrayList<Integer>();
			List<Post> posts = new ArrayList<Post>();
			
			Post post1 = new Post();
			post1.setPostId(1000);
			post1.setTitle("Lucifer");
			post1.setContent(PostType.VIDEO_IMAGE);
			post1.setCreatedDateTime(LocalDateTime.now());
			post1.setFlair("Deckerstar");
			post1.setNotSafeForWork(false);
			post1.setOriginalContent(true);
			post1.setVotes(10000);
			post1.setVoteUp(false);
			post1.setSpoiler(true);
			
			Mockito.when(postRepo.findById(1000)).thenReturn(Optional.of(post1));
			posts.add(post1);
			p.add(post1.getPostId());
			
			
			// Creating communityDto object using constructor
			CommunityInputDto com = new CommunityInputDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,p);
			
			// Creating community object and setting values
			Community newCommunity = new Community();
			newCommunity.setCommunityId(com.getCommunityId());
			newCommunity.setCommunityDescription(com.getCommunityDescription());
			newCommunity.setTotalMembers(com.getTotalMembers());
			newCommunity.setOnlineMembers(com.getOnlineMembers());
			newCommunity.setImage(com.getImage());
			newCommunity.setCreatedOn(com.getCreatedOn());
			newCommunity.setPostRulesAllowed(com.getPostRulesAllowed());
			newCommunity.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
			newCommunity.setBanningPolicy(com.getBanningPolicy());
			newCommunity.setFlairs(com.getFlairs());
			
			// adding new communities top community list
			communities.add(newCommunity);
			
			// Returning newCommunity when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newCommunity));
			
		}
		blogger.setCommunities(communities);
		
		List<Integer> postIds = new ArrayList<>();
		postIds.add(10);
		List<Post> posts = new ArrayList<>();
		Post post1 = new Post();
		post1.setPostId(10);
		post1.setTitle("Lucifer");
		post1.setContent(PostType.VIDEO_IMAGE);
		post1.setCreatedDateTime(LocalDateTime.now());
		post1.setFlair("Deckerstar");
		post1.setNotSafeForWork(false);
		post1.setOriginalContent(true);
		post1.setVotes(10000);
		post1.setVoteUp(false);
		post1.setSpoiler(true);
		
		Mockito.when(postRepo.findById(10)).thenReturn(Optional.of(post1));
		posts.add(post1);
		
		blogger.setPosts(posts);

		Mockito.when(blogRepo.findById(100)).thenReturn(Optional.of(blogger));

		// delete has void return type so do nothing is used
		doNothing().when(blogRepo).deleteById(blogger.getUserId());
		
		blogSer.deleteBlogger(bloggerInput.getUserId());

	}

	@Test
	public void updateBloggerTest() throws IdNotFoundException {
		
		BloggerInputDto bloggerInput = new BloggerInputDto();
		
		// Setting the values
		bloggerInput.setUserId(100);
		bloggerInput.setBloggerName("Abc");
		bloggerInput.setKarma(20);
		
		// Adding commentIds to the list
		List<Integer> commentIds = new ArrayList<>();
		commentIds.add(16);
		
		bloggerInput.setCommentIds(commentIds);
		
		Blogger blogger = new Blogger();
		blogger.setBloggerName(bloggerInput.getBloggerName());
		blogger.setUserId(bloggerInput.getUserId());
		blogger.setKarma(bloggerInput.getKarma());
		
		// List to store comments 
		List<Comment> comments = new ArrayList<>();
		for(Integer id : bloggerInput.getCommentIds()) {
			
			// Creating comment object and setting values
			Comment newComment = new Comment();
			newComment.setCommentId(26);
			newComment.setCommentDescription("Awesome");
			newComment.setVotes(10);
			
			// adding new comments top comment list
		    comments.add(newComment);
		    
			// Returning newComment when findById is called
			Mockito.when(commentRepo.findById(id)).thenReturn(Optional.of(newComment));
		}
		
		blogger.setComments(comments);
		
		// Storing community ids
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(61);
		
		// List to store community 
		List<Community> communities = new ArrayList<>();
		
		for(Integer id : bloggerInput.getCommunityIds()) {
			File fw = new File("abc.jpg");
			
			List<String> glist = new ArrayList<String>();
			glist.add("Hockey");
			glist.add("Cricket");
			glist.add("Tennis");
			
			List<String> galist = new ArrayList<String>();
			galist.add("Tours");
			galist.add("Furniture");
			galist.add("Houses");
			
			List<String> bp = new ArrayList<String>();
			bp.add("Cheating");
			bp.add("Drugs");
			bp.add("Misuse");
			
			List<String> f = new ArrayList<String>();
			f.add("SportsNews");
			
			List<Integer> p = new ArrayList<Integer>();
			List<Post> posts = new ArrayList<Post>();
			
			Post post1 = new Post();
			post1.setPostId(1000);
			post1.setTitle("Lucifer");
			post1.setContent(PostType.VIDEO_IMAGE);
			post1.setCreatedDateTime(LocalDateTime.now());
			post1.setFlair("Deckerstar");
			post1.setNotSafeForWork(false);
			post1.setOriginalContent(true);
			post1.setVotes(10000);
			post1.setVoteUp(false);
			post1.setSpoiler(true);
			
			Mockito.when(postRepo.findById(1000)).thenReturn(Optional.of(post1));
			posts.add(post1);
			p.add(post1.getPostId());
			
			
			// Creating communityDto object using constructor
			CommunityInputDto com = new CommunityInputDto(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f,p);
			
			// Creating community object and setting values
			Community newCommunity = new Community();
			newCommunity.setCommunityId(com.getCommunityId());
			newCommunity.setCommunityDescription(com.getCommunityDescription());
			newCommunity.setTotalMembers(com.getTotalMembers());
			newCommunity.setOnlineMembers(com.getOnlineMembers());
			newCommunity.setImage(com.getImage());
			newCommunity.setCreatedOn(com.getCreatedOn());
			newCommunity.setPostRulesAllowed(com.getPostRulesAllowed());
			newCommunity.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
			newCommunity.setBanningPolicy(com.getBanningPolicy());
			newCommunity.setFlairs(com.getFlairs());
			
			// adding new communities top community list
			communities.add(newCommunity);
			
			// Returning newCommunity when findById is called
			Mockito.when(comRepo.findById(id)).thenReturn(Optional.of(newCommunity));
			
		}
		blogger.setCommunities(communities);
		
		List<Integer> postIds = new ArrayList<>();
		postIds.add(10);
		List<Post> posts = new ArrayList<>();
		Post post1 = new Post();
		post1.setPostId(10);
		post1.setTitle("Lucifer");
		post1.setContent(PostType.VIDEO_IMAGE);
		post1.setCreatedDateTime(LocalDateTime.now());
		post1.setFlair("Deckerstar");
		post1.setNotSafeForWork(false);
		post1.setOriginalContent(true);
		post1.setVotes(10000);
		post1.setVoteUp(false);
		post1.setSpoiler(true);
		
		Mockito.when(postRepo.findById(10)).thenReturn(Optional.of(post1));
		posts.add(post1);
		
		blogger.setPosts(posts);

		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);

		
		Blogger updatedBlogger = blogSer.updateBlogger(bloggerInput);

		assertEquals(100, updatedBlogger.getUserId());
		assertEquals("Abc", updatedBlogger.getBloggerName());
		assertEquals(20, updatedBlogger.getKarma());
		assertEquals(1, updatedBlogger.getComments().size());
		assertEquals(1, updatedBlogger.getCommunities().size());
		assertEquals(1, updatedBlogger.getPosts().size());
	}
	
	@Test
	void viewBloggerListByCommunityIdTest() throws IdNotFoundException {

		Blogger blogger1 = new Blogger();
		blogger1.setUserId(25);
		blogger1.setBloggerName("Ross");
		blogger1.setKarma(50);
		
		Blogger blogger2 = new Blogger();
		blogger2.setUserId(26);
		blogger2.setBloggerName("Joey");
		blogger2.setKarma(500);
		
		List<Blogger> bloggers = new ArrayList<>();
		
		Mockito.when(blogRepo.viewBloggerListByCommunityId(61)).thenReturn(bloggers);
		
		List<BloggerOutputDto> allBloggers = blogSer.viewBloggerListByCommunityId(61);
		
		assertEquals(2, allBloggers.size());
	}
	
	

	

}
