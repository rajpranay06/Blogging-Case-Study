package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.bean.Award;

import com.example.demo.bean.Post;
import com.example.demo.dto.PostInputDto;
import com.example.demo.dto.PostOutputDto;
import com.example.demo.service.IPostService;

@RestController
public class PostController {
	
	@Autowired           // Creates object to the class
	IPostService postServ;
	
	// Adding Post
	@PostMapping("/posts")
	ResponseEntity<Post> addPost(@Valid @RequestBody PostInputDto post){
		Post newPost = postServ.addPost(post);
		return new ResponseEntity<>(newPost, HttpStatus.ACCEPTED);
	}
	
	// Updating Post
	@PutMapping("/posts")
	ResponseEntity<Post> updatePost(@Valid @RequestBody PostInputDto post){
		Post updatedPost = postServ.updatePost(post);
		return new ResponseEntity<>(updatedPost, HttpStatus.ACCEPTED);
	}
	
	// Deleting Post
	@DeleteMapping("/posts/{id}")
	ResponseEntity<Post> deletePost(@PathVariable("id") int id){
		Post deletedPost = postServ.deletePost(id);
		return new ResponseEntity<>(deletedPost, HttpStatus.OK);
	}
	
	// Get Posts by Searching
	@GetMapping("/posts/bySearchString/{searchString}")
	ResponseEntity<List<Post>> getPostsBySearchString(@PathVariable("searchString") String searchStr){
		List<Post> posts = postServ.getPostBySearchString(searchStr);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	// Upvoting the post
	@PatchMapping("/posts/upVote/{postId}")
	void upVote(@PathVariable("postId") int postId, boolean isUpVote) {
		postServ.upVote(postId, isUpVote);
	}
	
	@GetMapping("/blogger/allPosts/{bloggerId}")
	ResponseEntity<List<PostOutputDto>> getPostsByBlogger(@PathVariable("bloggerId") int id){
		List<PostOutputDto> posts = postServ.getPostsByBlogger(id);
		return new ResponseEntity<>(posts,HttpStatus.OK);
		
	}
	@GetMapping("/posts/awards/{id}")
	ResponseEntity<List<PostOutputDto>> getPostByAwardId(@PathVariable("id") int id){
		List<PostOutputDto> posts = postServ.getPostByawardId(id);
		return new ResponseEntity<>(posts, HttpStatus.OK);
    
	//GetPosts With CommunityId
	@GetMapping("/posts/byCommunityId/{communityId}")
	ResponseEntity<List<Post>> listPostsByCommunityId(@PathVariable("communityId") int communityId)
	{
		List<Post> posts = postServ.listPostsByCommunityId(communityId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}

	// Get post by Comment id
	@GetMapping("/posts/byComment/{commentId}")
	ResponseEntity<PostOutputDto> getPostByCommentId(@PathVariable("commentId") int commentId){
		PostOutputDto post = postServ.getPostByCommentId(commentId);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
}
