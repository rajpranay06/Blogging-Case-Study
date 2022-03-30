package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Award;

@Repository
public interface IAwardRepository extends JpaRepository<Award, Integer> {

	@Query(value = "select a.* from post p join post_awards pa on p.post_id=pa.post_id join award a on pa.award_id= a.award_id where p.post_id=:id", nativeQuery = true)
	public List<Award> getAllAwardsByPostId(@Param("id") int id);
	
	@Query(value="select a.* from blogger b join blogger_and_award ba on b.blogger_id=ba.blogger_id join award a on ba.award_id=a.award_id where b.blogger_id=:blogger_id",nativeQuery=true)
	public List<Award> getAwardsByBlogId(@Param("blogger_id") int id);
}
