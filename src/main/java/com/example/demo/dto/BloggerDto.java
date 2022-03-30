package com.example.demo.dto;

import java.util.List;

import com.example.demo.bean.Award;
import com.example.demo.bean.UserEntity;

import lombok.Data;

@Data
public class BloggerDto {

	private int bloggerId;
	private String bloggerName;
	private int karma;
	private List<CommunityOutputDto> communities;
	private List<Award> awards;
	private UserOutputDto user;
}
