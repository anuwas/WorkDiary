/**
 * 
 */
package com.org.pack.wd.scrum.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ambigo
 * 15-Feb-2026 10:04:48 pm
 */
@Entity
@Table(name = "SPRINT")
@Setter
@Getter
public class Sprint {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "sprint_name", columnDefinition = "TEXT")
    private String sprintName;
    
    @Column(name = "sprint_goal", columnDefinition = "TEXT")
    private String sprintGoal;
    
    @Column(name = "sprint_activity", columnDefinition = "TEXT")
    private String sprintActivity;
    
    @Column(name = "sprint_start_date", columnDefinition = "TEXT")
    private Date sprintStartDate;
    
    @Column(name = "sprint_end_date", columnDefinition = "TEXT")
    private Date sprintEndDate;
    
    @Column(name = "sprint_status")
    private String sprintStatus;
    
    @Column(name = "sprint_retrospective", columnDefinition = "TEXT")
    private String sprintRetrospective;
    
    @Column(name = "CREATED_DATE",insertable=true,updatable=false)
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "MODIFIED_DATE",insertable=false,updatable=true)
    @UpdateTimestamp
    private Timestamp modifiedDate;

}
