package com.org.pack.wd.team.entiry;

import java.sql.Date;
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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TEAM_MEMBER_LEAVE")
@Setter
@Getter
public class TeamMemberLeave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "TEAM_MEMBER_LEAVE_ID")
	private long teamMemberLeaveId;
	
	@Column(name = "LEAVE_DATE")
	private Date leaveDate;
	
	@Column(name = "LEAVE_TYPE")
	private String leaveType;
	
	@Column(name = "COMMENT",columnDefinition = "TEXT")
	private String comment;
	
	
	@Column(name = "CREATED_DATE",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp createdDate;
	
	
	@Column(name = "MODIFIED_DATE",insertable=false,updatable=true)
	@UpdateTimestamp
	private Timestamp modifiedDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TEAM_MEMBER_ID", nullable=false)
    private TeamMember teamMember;

}
