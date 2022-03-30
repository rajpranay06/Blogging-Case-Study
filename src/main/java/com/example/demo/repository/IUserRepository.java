package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
	
	@Query(value = "select * from user_entity where user_id=(select blogger.id from blogger where blogger.user_id=:id)", nativeQuery = true)
	public UserEntity getUserByBloggerId(@Param("id") int id);
	
}
