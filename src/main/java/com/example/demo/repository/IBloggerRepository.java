package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Blogger;
//import com.example.demo.bean.Community;
import com.example.demo.exception.IdNotFoundException;

@Repository
public interface IBloggerRepository extends JpaRepository<Blogger, Integer> {

	@Query(value="select b.* from blogger b join comment c on b.user_id=c.user_id where c.comment_id=:commentId",nativeQuery=true)
	Blogger getBloggerByCommentId(@Param("commentId") int commentId);

}
