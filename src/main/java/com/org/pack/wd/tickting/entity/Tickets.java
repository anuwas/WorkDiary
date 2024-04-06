package com.org.pack.wd.tickting.entity;

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

@Entity
@Table(name = "TICKETS")
@Setter
@Getter
public class Tickets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name = "TICKET_NUMBER")
	private long ticketNumber;
	
	@Column(name = "TICKET_PRIORITY")
	private String ticketPriority;
	
	@Column(name = "TICKET_STATUS")
	private String ticketStatus;
	
	@Column(name = "TICKET_TYPE")
	private String ticketType;
	
	@Column(name = "TICKET_SUMMARY",columnDefinition = "TEXT")
	private String ticketSummary;
	
	@Column(name = "TICKET_DESCRIPTION",columnDefinition = "TEXT")
	private String ticketDescription;
	
	@Column(name = "TICKET_CLOSING_NOTE",columnDefinition = "TEXT")
	private String ticketClosingNote;
	
	
	@Column(name = "DEFECT_AREA") // ui
	private String defectArea;
	
	@Column(name = "LINKED_TICKET")
	private long linkedTicket;
	
	@Column(name = "TICKET_SLA_KPI")
	private String ticketSlaKpi;
	
	@Column(name = "TICKET_SLA_KPI_NOTE", columnDefinition = "TEXT")
	private String ticketSlaKpiNote;
	
	@Column(name = "COMMENT", columnDefinition = "TEXT")
	private String comment;
	
	@Column(name = "APPLICATION")
	private String applicaiton;
	
	@Column(name = "APPLICATION_CATEGORY")
	private String applicationCategory;
	
	@Column(name = "TICKET_WORKER")
	private String ticketWorker;
	
	@Column(name = "TICKET_ENVIRONMENT")
	private String ticketEnvironment;
	
	@Column(name = "TICKET_CREATED_DATE")
	private Date ticketCreatedDate;
	
	@Column(name = "TICKET_CLOSE_DATE")
	private Date ticketClosedDate;
	
	@Column(name = "CREATED_DATE",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp createdDate;
	
	
	@Column(name = "MODIFIED_DATE",insertable=false,updatable=true)
	@UpdateTimestamp
	private Timestamp modifiedDate;

}
