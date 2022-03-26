package com.example.demo.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Comment {

	@Id
	@GeneratedValue
	private int commentId;
	@Size(min = 2, max=20, message = "Comment of this size is not allowed")
	@NotEmpty
	private String commentDescription;
	private int votes;
	private boolean voteUp;
	
}