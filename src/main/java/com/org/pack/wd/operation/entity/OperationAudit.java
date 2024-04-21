package com.org.pack.wd.operation.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "OPERATION_AUDIT")
@Setter
@Getter
@ToString
public class OperationAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "OPERATION_AUDITID")
	private long operationAuditid;
	
	@Column(name = "OPERATION_ACTIVITY",columnDefinition = "TEXT")
	private String operationActivity;
	
	@Column(name = "ACTIVITY_CREATED_DATE",insertable=true,updatable=true)
	@CreationTimestamp
	private Timestamp activityCreatedDate;
	
	   
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OPERATION_ID", nullable=false)
    private Operation operation;

}
