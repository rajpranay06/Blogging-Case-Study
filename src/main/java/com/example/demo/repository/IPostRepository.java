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
}
