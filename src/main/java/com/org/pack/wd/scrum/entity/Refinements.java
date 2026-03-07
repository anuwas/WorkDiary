package com.org.pack.wd.scrum.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "REFINEMENTS")
@Setter
@Getter
public class Refinements {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "REFINEMENT_DATE")
	private Date refinementDate;

	@Column(name = "CREATED_DATE",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp createdDate;
	
	@Column(name = "APPLICATION_NAME")
	private String applicationName;

	@Column(name = "JIRA_CARDS",columnDefinition = "TEXT")
	private String jiraCards;
	
	@Column(name = "REFINEMENT_MINUTES",columnDefinition = "TEXT")
	private String refinementMinutes;

}
