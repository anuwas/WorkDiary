package com.org.pack.wd.entity;



import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "REFINEMENTS")
@Setter
@Getter
public class Refinements {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "REFINEMENT_DATE",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp refinementDate;
	
	@Column(name = "APPLICATION_NAME")
	private String applicationName;
	
	@Column(name = "REFINEMENT_MINUTES",columnDefinition = "TEXT")
	private String refinementMinutes;

}
