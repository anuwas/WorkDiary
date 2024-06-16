package com.org.pack.wd.tickting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pack.wd.application.entity.Applications;
import com.org.pack.wd.application.repository.ApplicationsRepository;
import com.org.pack.wd.tickting.entity.TicketAppCategory;
import com.org.pack.wd.tickting.entity.TicketDefectArea;
import com.org.pack.wd.tickting.entity.TicketEnvironment;
import com.org.pack.wd.tickting.entity.TicketWorker;
import com.org.pack.wd.tickting.entity.Tickets;
import com.org.pack.wd.tickting.entity.Ticketstatus;
import com.org.pack.wd.tickting.entity.Tickettype;
import com.org.pack.wd.tickting.repository.TicketAppCategoryRepository;
import com.org.pack.wd.tickting.repository.TicketDefectAreaRepository;
import com.org.pack.wd.tickting.repository.TicketEnvironmentRepository;
import com.org.pack.wd.tickting.repository.TicketWorkerRepository;
import com.org.pack.wd.tickting.repository.TicketsRepository;
import com.org.pack.wd.tickting.repository.TicketstatusRepository;
import com.org.pack.wd.tickting.repository.TickettypeRepository;

@Service
public class TicketingService {
	
	@Autowired
	TicketsRepository ticketsRepository;
	
	@Autowired 
	ApplicationsRepository applicationsRepository;
	
	@Autowired
	TicketstatusRepository ticketstatusRepository;
	
	@Autowired
	TicketWorkerRepository ticketWorkerRepository;
	
	@Autowired
	TickettypeRepository tickettypeRepository;
	
	@Autowired
	TicketAppCategoryRepository ticketAppCategoryRepository;
	
	@Autowired
	TicketDefectAreaRepository ticketDefectAreaRepository;
	
	@Autowired
	TicketEnvironmentRepository ticketEnvironmentRepository;
	
	public List<Tickets> getAllTickets(){
		return (List<Tickets>) ticketsRepository.findAll();
	}
	public void saveTicket(Tickets ticketsObject) {
		  ticketsRepository.save(ticketsObject);
	}
	
	public List<String> getAllApplicationName() {
		return applicationsRepository.findAll().stream()
				.map(Applications::getApplicationName).collect(Collectors.toList());
	}
	
	public List<String> getAllWorkersName(){
		return ticketWorkerRepository.findAll().stream()
				.map(TicketWorker::getWorkerShortName).collect(Collectors.toList());
	}
	
	public List<String> getAllTicketStatusName(){
		return ticketstatusRepository.findAll().stream()
					.map(Ticketstatus::getTicketStatus).collect(Collectors.toList());
	}
	
	public List<String> getAllTicketTypeNames(){
		return tickettypeRepository.findAll().stream()
				.map(Tickettype::getTicketType).collect(Collectors.toList());
	}
	
	public List<String> getAllTicketAppCategoryName(){
		return ticketAppCategoryRepository.findAll().stream()
				.map(TicketAppCategory::getCategoryName).collect(Collectors.toList());
	}
	
	public List<String> getAllTicketDefectAreaName(){
		return ticketDefectAreaRepository.findAll().stream()
				.map(TicketDefectArea::getDefectArea).collect(Collectors.toList());
	}
	
	public List<String> getAllTicketEnvironmentName(){
		return ticketEnvironmentRepository.findAll().stream()
				.map(TicketEnvironment::getEnvironmentName).collect(Collectors.toList());
	}

}
