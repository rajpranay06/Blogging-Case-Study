package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Award {
	
	@Id
	@GeneratedValue
	private int awardId;
	private Coin coin;
}
