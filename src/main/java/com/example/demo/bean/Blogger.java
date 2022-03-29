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

import com.example.demo.dto.PostDto;

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


//	private List<Post> posts;
//	private List<Comment> comments;
//	private List<Post> upvoted;
//	private List<Post> downvoted;
	
	//OneToMany-One Blogger can comment many times
	@OneToMany(cascade = CascadeType.MERGE)
        @JoinColumn(name = "user_id")
	private List<Comment> comments;
	
	
//	private Award awardsReceived;
//	private Award awardsGiven;
//	private List<Community> communities;
	
	
}
	//ManyToMany-Many Bloggera can be a part of many communities
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name = "blogger_and_communities",
			joinColumns = { @JoinColumn(name="userId")},
			inverseJoinColumns = { @JoinColumn(name="communityId")})
	private List<Community> communities;
	
	private int karma;
	
        //OneToMany-One Blogger can have many posts
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private List<Post> posts;

	
	
}
