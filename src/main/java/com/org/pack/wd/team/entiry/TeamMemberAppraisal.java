package com.org.pack.wd.team.entiry;

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
import org.hibernate.annotations.UpdateTimestamp;

import com.org.pack.wd.operation.entity.Operation;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TEAM_MEMBER_APPRAISAL")
@Setter
@Getter
public class TeamMemberAppraisal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "TEAM_MEMBER_APPRAISAL_ID")
	private long teamMemberAppraisalId;
	
	@Column(name = "FINANCIAL_YEAR")
	private String financialYear;
	
	@Column(name = "ASSESSMENT_SESSION") // h2 q1
	private String assessmentSession;
	
	@Column(name = "GOES_WELL",columnDefinition = "TEXT")
	private String goesWell;
	
	@Column(name = "GOES_NOT_WELL",columnDefinition = "TEXT")
	private String goesNotWell;
	
	@Column(name = "IMPROVEMENT_AREAS",columnDefinition = "TEXT")
	private String improvementAreas;
	
	@Column(name = "DELIVERY_STAT",columnDefinition = "TEXT") // number of tickets/CR
	private String deliveryStat;
	
	@Column(name = "COMMENTS",columnDefinition = "TEXT") // general comment
	private String comments;
	
	@Column(name = "LEAVE_STAT",columnDefinition = "TEXT") // Leave statistics
	private String leaveStat;
	
	@Column(name = "LEAVE_COUNT") // Leave statistics
	private int leaveCount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TEAM_MEMBER_ID", nullable=false)
    private TeamMember teamMember;
	
		
	@Column(name = "MODIFIED_DATE",insertable=false,updatable=true)
	@UpdateTimestamp
	private Timestamp modifiedDate;

}
