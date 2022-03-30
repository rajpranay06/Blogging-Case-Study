package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.bean.Comment;
public interface ICommentRepository extends JpaRepository<Comment, Integer> {

	@Query(value = "select * from comment c join blogger b on c.blogger_id=b.blogger_id where b.blogger_id=:bloggerId",nativeQuery=true)
	public List<Comment> getCommentsByBlogger(@Param("bloggerId") int bloggerId);
	
	@Query(value = "SELECT c.* from comment c join post p on c.post_id = p.post_id where c.post_id = :postId", nativeQuery = true)
	public List<Comment> getAllCommentsOfPost(@Param("postId") int postId);
	
}
