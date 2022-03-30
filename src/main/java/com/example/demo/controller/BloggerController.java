package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.service.IBloggerService;

@RestController
public class BloggerController {

	@Autowired
	IBloggerService blogServ;
	

	// Add new blogger
	@PostMapping("/bloggers")
	ResponseEntity<Blogger> addBlogger(@RequestBody Blogger blogger) {
		Blogger newBlog = blogServ.addBlogger(blogger);
		return new ResponseEntity<>(newBlog, HttpStatus.CREATED);

	}

	// Get all Bloggers
	@GetMapping("/bloggers")
	List<Blogger> viewAllBloggers() {
		return blogServ.viewAllBloggers();
	}

	// Add new blogger with dto
	@PostMapping("/bloggers/dto")
	ResponseEntity<Blogger> addBloggerDto(@Valid @RequestBody BloggerInputDto blogger) {
		Blogger newDtoBlog = blogServ.addBloggerDto(blogger);
		return new ResponseEntity<>(newDtoBlog, HttpStatus.CREATED);

	}

	// Update blogger
	@PutMapping("/blogger")
	ResponseEntity<Blogger> updateBlogger(@RequestBody BloggerInputDto blogger) throws IdNotFoundException {
		Blogger updatedBlog = blogServ.updateBlogger(blogger);
		return new ResponseEntity<>(updatedBlog, HttpStatus.CREATED);

	}

	// Delete Blog
	@DeleteMapping("/blogger/{bloggerId}")
	ResponseEntity<String> deleteBlogger(@PathVariable("bloggerId")  int bloggerId) throws IdNotFoundException {
		blogServ.deleteBlogger(bloggerId);
		return new ResponseEntity<>("Blogger with id " + bloggerId + " is deleted", HttpStatus.OK);

	}
	
	//Get Blogger by Id
	@GetMapping("/bloggers/{userId}")
	Blogger viewBlogger(@PathVariable("userId") int bloggerId) throws IdNotFoundException {
		return blogServ.viewBlogger(bloggerId);

	}
	// Get Blogger by Comment Id
	@GetMapping("/bloggers/byCommentId/{commentId}")
	BloggerOutputDto getBloggerByCommentId(@PathVariable("commentId") int commentId) {
		return blogServ.getBloggerByCommentId(commentId);
	}

	//Get Bloggers by Community Id
	@GetMapping("/blogger/byCommunity/{communityId}")
	ResponseEntity<List<BloggerOutputDto>> viewBloggerListByCommunityId(@PathVariable("communityId") int communityId) throws IdNotFoundException{
		List<BloggerOutputDto> bloggers = blogServ.viewBloggerListByCommunityId(communityId);
		return new ResponseEntity<>(bloggers, HttpStatus.OK);

	}
	
	//Get Blogger by Post Id
	@GetMapping("/blogger/byPost/{postId}")
	ResponseEntity<BloggerOutputDto> getBloggerByPostId(@PathVariable("postId") int postId){
		BloggerOutputDto blogger = blogServ.getBloggerByPostId(postId);
		return new ResponseEntity<>(blogger, HttpStatus.OK);
	}
	
	// Get Blogger by Award Id
	@GetMapping("/bloggers/awardId/{awardId}")
	ResponseEntity<List<BloggerOutputDto>> getBloggerByAwardId(@PathVariable("awardId") int awardId) throws IdNotFoundException {
		List<BloggerOutputDto> bloggers =blogServ.getBloggerByAwardId(awardId);
		return new ResponseEntity<>(bloggers, HttpStatus.OK);

	}
	
	
	
}
