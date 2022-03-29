package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.example.demo.bean.Post;

@Repository
public interface IPostRepository extends JpaRepository<Post, Integer> {
	
	// JPQL Query to find posts using LIKE operator
	@Query(value = "SELECT * FROM post where title LIKE :searchStr", nativeQuery = true)
	List<Post> getPostBySearchString(@Param("searchStr") String searchStr);

	@Query(value = " SELECT * FROM post p join blogger b on p.user_id=b.user_id where b.user_id=:user_id", nativeQuery = true)
	List<Post> getPostsByBlogger(@Param("user_id") int bloggerId);
}
