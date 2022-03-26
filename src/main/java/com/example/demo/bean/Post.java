package com.example.demo.bean;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity       
@Data          // Used to create all getter setter constructors and tostring methods
public class Post {
	
	@Id                  // Making postId as primary key
	@GeneratedValue      // Auto generation of values
	private int postId;
	@Size(min = 3, max = 50, message = "Title should be of 3 to 50 characters")
	private String title;
	//private Blogger createdBy;
	private PostType content;
	//private List<Files> data;
	//private Award awardsReceived;
	private LocalDateTime createdDateTime;
	//private List<Comment> comments;
	private int votes;
	private boolean voteUp;
    private boolean notSafeForWork;
    private boolean spoiler;
    private boolean originalContent;
    private String flair;
    //private Community community;
    
}
