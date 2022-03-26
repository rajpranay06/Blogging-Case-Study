package com.example.demo.bean;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserErrorResponse {

	private int status;
	private String msg;
	//private long timestamp;
	private LocalDateTime timeStamp;
}
