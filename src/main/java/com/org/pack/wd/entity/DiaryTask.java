package com.org.pack.wd.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DIARY_TASK")
@Setter
@Getter
public class DiaryTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "TASK_SUMMARY",columnDefinition = "TEXT")
	private String taskSummary;

	@Column(name = "TASK_DESCRIPTION",columnDefinition = "TEXT")
	private String taskDescriptoin;
	
	@Column(name = "TASK_DATE")
	private Date taskDate;
	
	@Column(name = "TASK_STATUS")
	private String taskStatus;
	
		
	@Column(name = "CREATED_DATE")
	@CreationTimestamp
	private Timestamp createdDate;
	
}
