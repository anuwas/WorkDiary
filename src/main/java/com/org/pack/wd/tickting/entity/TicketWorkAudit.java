package com.org.pack.wd.tickting.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TICKET_WORK_AUDIT")
@Setter
@Getter
public class TicketWorkAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "ticket_id")
    private Tickets tickets;
	
	@Column(name = "WORK_ACTIVITY")
	private String workActivity;
	
	@Column(name = "WORKER")
	private String worker;
	
	@Column(name = "WORK_DATE")
	private Date workDate;
	
	@Column(name = "CREATED_DATE",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp createdDate;

}
