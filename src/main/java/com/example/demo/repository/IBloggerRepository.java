package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Blogger;
import com.example.demo.exception.IdNotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Blogger;

@Repository
public interface IBloggerRepository extends JpaRepository<Blogger, Integer> {

	@Query(value="select b.* from blogger b join comment c on b.user_id=c.user_id where c.comment_id=:commentId",nativeQuery=true)
	public Blogger getBloggerByCommentId(@Param("commentId") int commentId);

	@Query(value = "SELECT b.* from blogger b join blogger_and_communities bc on b.user_id = bc.user_id join community c on bc.community_id = c.community_id where c.community_id = :communityId", nativeQuery = true)
	public List<Blogger> viewBloggerListByCommunityId(@Param("communityId") int communityId);
	
}
