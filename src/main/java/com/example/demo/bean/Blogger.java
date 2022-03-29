package com.example.demo.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blogger {
	@Id
	@GeneratedValue
	private int userId;
	@NotEmpty(message="Name shouldn't be empty")
	@Size(min=3, max=50, message="Min 3 characters required")
	private String bloggerName;
//	private List<Post> posts;
//	private List<Comment> comments;
//	private List<Post> upvoted;
//	private List<Post> downvoted;
//	private Award awardsReceived;
//	private Award awardsGiven;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name = "blogger_and_communities",
			joinColumns = { @JoinColumn(name="userId")},
			inverseJoinColumns = { @JoinColumn(name="communityId")})
	private List<Community> communities;
	
	private int karma;
	
}
