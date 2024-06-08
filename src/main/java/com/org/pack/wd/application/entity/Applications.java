package com.org.pack.wd.application.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


public class Applications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "APPLICATION_NAME")
	private String applicationName;
	
	@Column(name = "APPLICATION_ABBREVIATION")
	private String applicaitonAbbreviation;
	
	@Column(name = "APPLICATION_DEASCRIPTION",columnDefinition = "TEXT")
	private String applicationDescription;
	
	@Column(name = "BUSINESS_STREAM")
	private String businessStream;
	
	@Column(name = "MODULES")
	private String modules;
	
	@Column(name = "INTERFACES",columnDefinition = "TEXT")
	private String interfaces;
	
	@Column(name = "TECHNOLOGY",columnDefinition = "TEXT")
	private String technology;
	
	@Column(name = "APPLICATION_STATUS")
	private String applicationStatus;
	
	@Column(name = "RUNBOOK",columnDefinition = "TEXT")
	private String runbook;

}
