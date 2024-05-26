package com.org.pack.wd.team.entiry;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.org.pack.wd.operation.entity.OperationAudit;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TEAM_MEMBER")
@Setter
@Getter
public class TeamMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "TEAM_MEMBER_ID")
	private long teamMemberId;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "CUSTOMER_EMP_ID")
	private String customerEmpId;
	
	@Column(name = "ASSIGNED_PROJECT") // GL AT
	private String assignedProject;
	
	@Column(name = "PROJECT_SQUAD") // Core
	private String projectSquad;
	
	@Column(name = "ORGNIZATION_EMP_ID")
	private String organizationEmpId;
	
	@Column(name = "ORGNIZATION_DESIGNATION")
	private String organizationDesignation;
	
	@Column(name = "ORGNIZATION_DEPARTMENT")
	private String organizationDepartment;
	
	@Column(name = "ORGNIZATION_PROJECT_ID")
	private String organizationProjectId;
	
	@Column(name = "SKILLSET")
	private String skillset;
	
	@Column(name = "STATUS") // Active
	private String status;
	
	@Column(name = "PHONE_NO") 
	private String phoneNo;
	
	@Column(name = "Location") 
	private String location;
	
	@Column(name = "OTHER_INFO") 
	private String otherInfo;
	
	@Column(name = "CREATED_DATE",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp createdDate;
	
	@OneToMany(mappedBy="teamMember")
    private List<TeamMemberAppraisal> teamMemberAppraisal;
	
	@OneToMany(mappedBy="teamMember")
    private List<TeamMemberLeave> teamMemberLeave;

}
