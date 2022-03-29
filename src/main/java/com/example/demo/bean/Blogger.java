package com.example.demo.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	private int karma;
	
	public Blogger(int userId,@NotEmpty(message = "Name shouldn't be empty") @Size(min = 3, max = 50, message = "Min 3 characters required") String bloggerName,int karma) {
		super();
		this.userId = userId;
		this.bloggerName = bloggerName;
		this.karma = karma;
	}
	
	//OneToMany-One Blogger can comment many times
	@OneToMany(cascade = CascadeType.MERGE)
        @JoinColumn(name = "user_id")
	private List<Comment> comments;
	
	//ManyToMany-Many Bloggers can be a part of many communities
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name = "blogger_and_communities",
			joinColumns = { @JoinColumn(name="userId")},
			inverseJoinColumns = { @JoinColumn(name="communityId")})
	private List<Community> communities;
	
	
    //OneToMany-One Blogger can have many posts
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private List<Post> posts;

	
	
}
