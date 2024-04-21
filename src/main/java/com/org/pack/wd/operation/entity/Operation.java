package com.org.pack.wd.operation.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "OPERATION")
@Setter
@Getter
@ToString
public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "OPERATION_ID")
	private long operationId;
	
	@Column(name = "OPERATION_NAME")
	private String operationName;
	
	@Column(name = "OPERATION_DESCRIPTION",columnDefinition = "TEXT")
	private String operationDescription;
	
	@Column(name = "OPERATION_STATUS")
	private String operationStatus = "Active";
	
	@Column(name = "OPERATION_CREATED_DATE",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp operationCreatedDate;
	
	@OneToMany(mappedBy="operation")
    private List<OperationAudit> operationAudit;

}
