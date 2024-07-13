package com.org.pack.wd.tickting.helper;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.org.pack.wd.tickting.entity.Tickets;

@Component
public class TicketingJPACriteriaHelper {
	
	@Autowired
	EntityManager em;
	
	public Page<Tickets> retrivePageTicketsBySearchandSort(long ticketNumber,String ticketType,String ticketStatus,String ticketPriority,String applicaiton,String ticketEnvironment,String ticketCreatedDate, String ticketClosedDate,Pageable pageable) throws ParseException{
		CriteriaBuilder builder =  em.getCriteriaBuilder();
		CriteriaQuery<Tickets> criteria = builder.createQuery(Tickets.class);
		Root<Tickets> supportItemRoot = criteria.from(Tickets.class);
		 List<Predicate> predicates = new ArrayList<Predicate>();
		 
		
		 if (ticketNumber>0) {
			  predicates.add(builder.equal(supportItemRoot.get("ticketNumber"), ticketNumber)); 
		}
		 if (!ticketType.equals("All")) {
			  predicates.add(builder.equal(supportItemRoot.get("ticketType"), ticketType)); 
		}
		 if (!ticketStatus.equals("All")) {
			  predicates.add(builder.equal(supportItemRoot.get("ticketStatus"), ticketStatus)); 
		}
		 if (!ticketPriority.equals("All")) {
			  predicates.add(builder.equal(supportItemRoot.get("ticketPriority"), ticketPriority)); 
		}
		 
		  if (!applicaiton.equals("All")) {
		  predicates.add(builder.equal(supportItemRoot.get("applicaiton"),   applicaiton)); 
		  }
		  if (!ticketEnvironment.equals("All")) {
			  predicates.add(builder.equal(supportItemRoot.get("ticketEnvironment"),   ticketEnvironment)); 
		}
		  /*
		  if (!ticketCreatedDate.equals("")) {
			 
			  SimpleDateFormat myformatter = new SimpleDateFormat("yyyy-MM-dd");
			  java.util.Date date = myformatter.parse(ticketCreatedDate);
			  
			  predicates.add(builder.greaterThanOrEqualTo(supportItemRoot.get("ticketCreatedDate"), date));
		}*/
		  
	
		if (!ticketCreatedDate.equals("") && !ticketClosedDate.equals("")) {
			 
			  SimpleDateFormat myformatter = new SimpleDateFormat("yyyy-MM-dd");
			  java.util.Date fromDate = myformatter.parse(ticketCreatedDate);
			  java.util.Date toDate = myformatter.parse(ticketClosedDate);			  
			  predicates.add(builder.between(supportItemRoot.get("ticketCreatedDate"), fromDate, toDate)); 
		}
		
		 
		 criteria.where(builder.and(predicates.toArray( new Predicate[predicates.size()])));
		 
		 

	        criteria.orderBy(builder.desc(supportItemRoot.get("createdDate")));
	        List<Tickets> result = em.createQuery(criteria).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
	        
	        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
	        Root<Tickets> supportItemRootRootCount = countQuery.from(Tickets.class);
	        countQuery.select(builder.count(supportItemRootRootCount)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

	        // Fetches the count of all SupItem as per given criteria
	        Long count = em.createQuery(countQuery).getSingleResult();

	        Page<Tickets> result1 = new PageImpl<>(result, pageable, count);
	        return result1;

	}
	
	public List<Tickets> retriveOpenTicketsBySearchandSort(String ticketType,String ticketStatus,String ticketPriority,String applicaiton,String ticketEnvironment){
		CriteriaBuilder builder =  em.getCriteriaBuilder();
		CriteriaQuery<Tickets> criteria = builder.createQuery(Tickets.class);
		Root<Tickets> supportItemRoot = criteria.from(Tickets.class);
		 List<Predicate> predicates = new ArrayList<Predicate>();
		 
		 predicates.add(builder.not(supportItemRoot.get("ticketStatus").in("Closed","Resolved","Fulfilled"))); 
		 
		 
		/*
		 if (!ticketType.equals("All")) {
			  predicates.add(builder.equal(supportItemRoot.get("ticketType"), ticketType)); 
		}
		 if (!ticketStatus.equals("All")) {
			  predicates.add(builder.equal(supportItemRoot.get("ticketStatus"), ticketStatus)); 
		}
		 if (!ticketPriority.equals("All")) {
			  predicates.add(builder.equal(supportItemRoot.get("ticketPriority"), ticketPriority)); 
		}
		 
		  if (!applicaiton.equals("All")) {
		  predicates.add(builder.equal(supportItemRoot.get("applicaiton"),   applicaiton)); 
		  }
		  if (!ticketEnvironment.equals("All")) {
			  predicates.add(builder.equal(supportItemRoot.get("ticketEnvironment"),   ticketEnvironment)); 
		}
		*/
		  criteria.where(builder.and(predicates.toArray( new Predicate[predicates.size()])));
			 
			 

	        criteria.orderBy(builder.desc(supportItemRoot.get("createdDate")));
	        List<Tickets> result = em.createQuery(criteria).getResultList();
	        return result;
	}

}
