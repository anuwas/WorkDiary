package com.org.pack.wd.tickting.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.pack.wd.entity.DiaryTask;
import com.org.pack.wd.tickting.entity.TicketWorkAudit;
import com.org.pack.wd.tickting.entity.Tickets;
import com.org.pack.wd.tickting.helper.TicketingJPACriteriaHelper;
import com.org.pack.wd.tickting.repository.TicketWorkAuditRepository;
import com.org.pack.wd.tickting.repository.TicketsRepository;
import com.org.pack.wd.tickting.service.TicketingService;
import com.org.pack.wd.util.ConstantProperties;

@Controller
public class TicketingController {
	
	@Autowired
	TicketingService ticketingService;
	
	@Autowired
	TicketsRepository ticketsRepository;
	
	@Autowired
	TicketingJPACriteriaHelper ticketingJPACriteriaHelper;
	
	@Autowired
	TicketWorkAuditRepository ticketWorkAuditRepository;
	
	
	@GetMapping("/tickets/ticket-form")
	public String taskForm(Model model) {
		Tickets ticketObject = new Tickets();
		String str="2015-03-31";  
	    Date date=Date.valueOf(str);//converting string into sql date  
		ticketObject.setTicketClosedDate(date);
        model.addAttribute("ticktObject", ticketObject);
        List<String> ticketStatusList = ticketingService.getAllTicketStatusName();
        List<String> ticketTypeList = ticketingService.getAllTicketTypeNames();
        List<String> ticketPrirityList = Arrays.asList("P5","P4","P3","P2","P1");
        List<String> applicaitonList = ticketingService.getAllApplicationName();
        List<String> applicaitonCategoryList = ticketingService.getAllTicketAppCategoryName();
        List<String> defectAreaList = ticketingService.getAllTicketDefectAreaName();
        List<String> workerList = ticketingService.getAllWorkersName();
        Map<Integer,String> taskPriorityMap = ConstantProperties.TASK_PRIORITY_MAP;
        model.addAttribute("ticketStatusList", ticketStatusList);
        model.addAttribute("ticketTypeList", ticketTypeList);
        model.addAttribute("ticketPrirityList", ticketPrirityList);
        model.addAttribute("applicaitonList", applicaitonList);
        model.addAttribute("applicaitonCategoryList", applicaitonCategoryList);
        model.addAttribute("defectAreaList", defectAreaList);
        model.addAttribute("ticketCloseDefaultCalue", "2024-01-01");
        model.addAttribute("ticketWorkersList", workerList);
        
        
		return "tickets/ticket-form";
	}
	
	@GetMapping("/tickets/ticket-form/{id}")
	public String taskForm(Model model,@PathVariable long id) {
		Optional<Tickets> ticketObject = ticketsRepository.findById(id);
		if(!ticketObject.isPresent()) {
			return "redirect:/tickets";
		}
		
		String str="2015-03-31";  
	    Date date=Date.valueOf(str);//converting string into sql date  
	    ticketObject.get().setTicketClosedDate(date);
        model.addAttribute("ticktObject", ticketObject.get());
        List<String> ticketStatusList = ticketingService.getAllTicketStatusName();
        List<String> ticketTypeList = ticketingService.getAllTicketTypeNames();
        List<String> ticketPrirityList = Arrays.asList("P5","P4","P3","P2","P1");
        List<String> applicaitonList = ticketingService.getAllApplicationName();
        List<String> applicaitonCategoryList = ticketingService.getAllTicketAppCategoryName();
        List<String> defectAreaList = ticketingService.getAllTicketDefectAreaName();
        List<String> workerList = ticketingService.getAllWorkersName();
        Map<Integer,String> taskPriorityMap = ConstantProperties.TASK_PRIORITY_MAP;
        model.addAttribute("ticketStatusList", ticketStatusList);
        model.addAttribute("ticketTypeList", ticketTypeList);
        model.addAttribute("ticketPrirityList", ticketPrirityList);
        model.addAttribute("applicaitonList", applicaitonList);
        model.addAttribute("applicaitonCategoryList", applicaitonCategoryList);
        model.addAttribute("defectAreaList", defectAreaList);
        model.addAttribute("ticketCloseDefaultCalue", "2024-01-01");
        model.addAttribute("ticketWorkersList", workerList);
        if(ticketObject.get().getTicketWorker()!=null) {
        	model.addAttribute("ticketWorkersListSelected", ticketObject.get().getTicketWorker());
        }else {
        	model.addAttribute("ticketWorkersListSelected", "No worker selected");
        }
        
        
        
		return "tickets/ticket-form-edit";
	}
	
	@GetMapping("/tickets/ticket-audit-form/{id}")
	public String ticketAuditForm(Model model,@PathVariable long id) {
		TicketWorkAudit newTicketWorkAudit = new TicketWorkAudit();
		Optional<Tickets> ticketObject = ticketsRepository.findById(id);
		List<TicketWorkAudit> allExistingAudit = ticketWorkAuditRepository.findAllByTickets(ticketObject.get());
		
		List<String> workerList = ticketingService.getAllWorkersName();
		model.addAttribute("ticketWorkersList", workerList);
		
		 model.addAttribute("newTicketWorkAudit", newTicketWorkAudit);
	     model.addAttribute("allExistingAudit", allExistingAudit); 
	     model.addAttribute("ticketObjectID", id); 
        
		return "tickets/ticket-work-audit-form";
	}
	
	/*
	@GetMapping("/tickets")
	public String getAllTickets(Model model) {
		List<Tickets> allTicketList = ticketingService.getAllTickets();
		 model.addAttribute("allTicketList", allTicketList);
		 return "tickets/tickets";
	}
	*/
	
	@GetMapping("/tickets")
	  public String getAll(
			  Model model, 
			  @RequestParam(required = false) String keyword,
			  @RequestParam(defaultValue = "1") int page,
			  @RequestParam(value = "ticketNumber", required = false,defaultValue = "0") long ticketNumber,
			  @RequestParam(value = "ticketPriority", required = false) String ticketPriority,
			  @RequestParam(value = "ticketType", required = false) String ticketType,
			  @RequestParam(value = "ticketStatus", required = false) String ticketStatus,
			  @RequestParam(value = "applicaiton", required = false) String applicaiton,
			  @RequestParam(value = "ticketCreatedDate", required = false) String ticketCreatedDate,
			  @RequestParam(value = "ticketClosedDate", required = false) String ticketClosedDate)	
	{
		int size = 50;
	    try {
	      List<Tickets> tutorials = new ArrayList<Tickets>();
	      Pageable paging = PageRequest.of(page - 1, size,Sort.by("createdDate").descending());

	      Page<Tickets> pageTuts;
	      if (keyword == null) {
	        pageTuts = ticketsRepository.findAll(paging);
	      } else {
	        //pageTuts = ticketsRepository.findByTicketStatusContainingIgnoreCase("Active", paging);
	    	  pageTuts = ticketingJPACriteriaHelper.retriveTicketsBySearchandSort(ticketNumber,ticketType, ticketStatus, ticketPriority,applicaiton,paging);
	        model.addAttribute("keyword", keyword);
	      }
	      
	      model.addAttribute("ticketNumber", ticketNumber);
	      model.addAttribute("ticketPriority", ticketPriority);
	      model.addAttribute("ticketType", ticketType);
	      model.addAttribute("ticketStatus", ticketStatus);
	      model.addAttribute("applicaiton", applicaiton);
	      model.addAttribute("ticketCreatedDate", ticketCreatedDate);
	      model.addAttribute("ticketClosedDate", ticketClosedDate);

	      tutorials = pageTuts.getContent();

	      model.addAttribute("allTicketList", tutorials);
	      model.addAttribute("currentPage", pageTuts.getNumber() + 1);
	      model.addAttribute("totalItems", pageTuts.getTotalElements());
	      model.addAttribute("totalPages", pageTuts.getTotalPages());
	      model.addAttribute("pageSize", size);
	      
	      List<String> ticketTypeList = ticketingService.getAllTicketTypeNames();
	      List<String> ticketPrirityList = Arrays.asList("P5","P4","P3","P2","P1");
	      List<String> applicaitonList = ticketingService.getAllApplicationName();
	      List<String> ticketStatusList = ticketingService.getAllTicketStatusName();
	      model.addAttribute("ticketTypeList", ticketTypeList);
	      model.addAttribute("ticketPrirityList", ticketPrirityList);
	      model.addAttribute("applicaitonList", applicaitonList);
	      model.addAttribute("ticketStatusList", ticketStatusList);
	      model.addAttribute("keyword", "search");
	      
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	    }

	    return "tickets/tickets";
	  }
	
	
	@PostMapping("/tickets/saveticket")
	public String createTutorial(Tickets tickets,Model model) {
		try {
			tickets.setTicketClosedDate(null);
			tickets = ticketsRepository.save(tickets);
			return "redirect:/tickets/ticket-form?id="+tickets.getId();
		} catch (Exception e) {
			return "tickets/task-form";
		}
	}
	
	@PostMapping("/tickets/saveaudit/{id}")
	public String createTicketWorkerAudit(TicketWorkAudit ticketWorkAudit,Model model,@PathVariable long id) {
		try {
			Optional<Tickets> ticketObject = ticketsRepository.findById(id);
			ticketWorkAudit.setTickets(ticketObject.get());
			ticketWorkAudit = ticketWorkAuditRepository.save(ticketWorkAudit);
			return "redirect:/tickets/ticket-audit-form/"+id;
		} catch (Exception e) {
			return "tickets/ticket-audit-form";
		}
	}
	
	 @PostMapping("/tickets/updateticket/{id}")
	    public String ticketManagerUpdate(Model model,@PathVariable long id,@ModelAttribute("ticktObject") Tickets tickets) {
		 
		 //tickets.setId(id);
		 if(!tickets.getTicketStatus().equals("Closed")) {
			 tickets.setTicketClosedDate(null);
		 }
		 ticketsRepository.save(tickets);
		 return "redirect:/tickets";
	    }
	
	

}
