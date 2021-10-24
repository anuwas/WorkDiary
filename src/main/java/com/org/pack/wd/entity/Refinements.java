package com.org.pack.wd.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name = "REFINEMENT_DATE")
	private Date refinementDate;
	
	@Column(name = "APPLICATION_NAME")
	private String applicationName;
	
	@Column(name = "REFINEMENT_MINUTES",columnDefinition = "TEXT")
	private String refinementMinutes;

}
