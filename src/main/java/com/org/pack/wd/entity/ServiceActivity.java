package com.org.pack.wd.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "SERVICE_ACTIVITY")
@Setter
@Getter
@ToString
public class ServiceActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name = "SERVICE_YEAR_NAME")
	private String serviceYearName;
	
	@Column(name = "SERVICE_YEAR")
	private int serviceYear;
	
	@Column(name = "SERVICE_MONTH_NAME")
	private String serviceMonthName;
	
	@Column(name = "SERVICE_MONTH")
	private int serviceMonth;
	
	@Column(name = "SERVICE_WEEK_NAME")
	private String serviceWeekName;
	
	@Column(name = "SERVICE_ACTIVITY_CATEGORY")
	private String serviceActivityCategory;
	
	@Column(name = "SERVICE_ACTIVITY_DETAIL",columnDefinition = "TEXT")
	private String serviceActivityDetail;
	
	@Column(name = "SERVICE_WEEK",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp serviceWeek;

}
