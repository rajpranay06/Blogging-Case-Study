package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.example.demo.bean.Award;
import com.example.demo.bean.Post;
import com.example.demo.dto.PostOutputDto;

@Repository
public interface IPostRepository extends JpaRepository<Post, Integer> {
	
	// JPQL Query to find posts using LIKE operator
	@Query(value = "SELECT * FROM post where title LIKE :searchStr", nativeQuery = true)

	List<Post> getPostBySearchString(@Param("searchStr") String searchStr);
	
	@Query(value = "select p.* from post p join post_awards pa on p.post_id=pa.post_id join award a on pa.award_id= a.award_id where a.award_id=:id", nativeQuery = true)
	List<Post> getAllPostsByAwardId(@Param("id") int id);

	public List<Post> getPostBySearchString(@Param("searchStr") String searchStr);
	
	// JPQL Query to get post by comment id
	@Query(value = "SELECT p.* from post p join comment c on p.post_id = c.post_id where c.comment_id = :commentId", nativeQuery = true)
	public Post getPostByCommentId(@Param("commentId") int commentId);
}
