package com.example.demo.bean;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity       
@Data          // Used to create all getter setter constructors and tostring methods
//@Table(name="Blogger")
@Data         // Used to create all getter setter constructors and tostring methods
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
	private int votes;
	private boolean voteUp;
    private boolean notSafeForWork;
    private boolean spoiler;
    private boolean originalContent;
    private String flair;
	
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "post_id")
	private List<Comment> comments;

    //private Community community;
    
  @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
    		name = "post_awards",
    		joinColumns = {@JoinColumn(name = "post_id") },
    		inverseJoinColumns = { @JoinColumn(name = "award_id") }
    )
    private List<Award> awards;
    
}
