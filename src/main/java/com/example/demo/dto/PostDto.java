package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.bean.Community;
import com.example.demo.bean.PostType;

import lombok.Data;

@Data
public class PostDto {

	private int postId;
	private String title;
	private PostType content;
	private LocalDateTime createdDateTime;
	private int votes;
	private boolean voteUp;
    private boolean notSafeForWork;
    private boolean spoiler;
    private boolean originalContent;
    private String flair;
    private Community community;
}
