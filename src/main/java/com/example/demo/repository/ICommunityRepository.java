package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Community;



@Repository
public interface ICommunityRepository extends JpaRepository<Community, Integer> {
	//JPQL Query to get all communities which are having same community description
	@Query(value="select * from community where community_description =:searchString",nativeQuery=true)
	
	List<Community> listAllCommunities(@Param("searchString") String searchString);
	
	Optional<List<Community>> findByCommunityDescription(String searchString);

	

}
	