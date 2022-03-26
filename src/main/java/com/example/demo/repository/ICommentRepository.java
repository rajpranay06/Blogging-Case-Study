package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {

}
