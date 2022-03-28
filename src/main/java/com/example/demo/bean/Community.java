
package com.example.demo.bean;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
	
	@Id
	@GeneratedValue
	private int communityId;

	private String communityDescription;
	private int totalMembers;
	private int onlineMembers;
	private File image;
	@Convert(converter = LocalDateConverter.class)
	private LocalDate createdOn;
	@ElementCollection
	@CollectionTable(name="Rules_Allowed")
	private List<String> postRulesAllowed;
	@ElementCollection
	@CollectionTable(name="Rules_DisAllowed")
	private List<String> postRulesDisAllowed;
	@ElementCollection
	@CollectionTable(name="Banning_policy")
	private List<String> banningPolicy;
	@ElementCollection
	@CollectionTable(name="flairs")
	private List<String> flairs;
}

