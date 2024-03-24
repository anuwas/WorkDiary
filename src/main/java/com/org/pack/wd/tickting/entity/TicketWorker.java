package com.org.pack.wd.tickting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TICKET_WORKER")
@Setter
@Getter
public class TicketWorker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "WORKER_SHORT_NAME")
	private String workerShortName;
	
	@Column(name = "WORKER_FULL_NAME")
	private String workerFullName;

}
