package com.example.demo.dto;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Data;
@Data
@NoArgsConstructor
public class CommunityDto {
	private int communityId;
	private String communityDescription;
	private int totalMembers;
	private int onlineMembers;
	private File image;
	private LocalDate createdOn;
	private List<String> postRulesAllowed;
	private List<String> postRulesDisAllowed;
	private List<String> banningPolicy;
	private List<String> flairs;
	public CommunityDto(int communityId, String communityDescription, int totalMembers, int onlineMembers, File image,
			LocalDate createdOn, List<String> postRulesAllowed, List<String> postRulesDisAllowed,
			List<String> banningPolicy, List<String> flairs) {
		super();
		this.communityId = communityId;
		this.communityDescription = communityDescription;
		this.totalMembers = totalMembers;
		this.onlineMembers = onlineMembers;
		this.image = image;
		this.createdOn = createdOn;
		this.postRulesAllowed = postRulesAllowed;
		this.postRulesDisAllowed = postRulesDisAllowed;
		this.banningPolicy = banningPolicy;
		this.flairs = flairs;
	}
}
