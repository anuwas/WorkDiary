/**
 *
 */
package com.org.pack.wd.skillup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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


/**
 * @author ambigo
 * 07-Mar-2025 1:19:50 am
 */
@Component
public class WordingCriteriaHelper {
	
	@Autowired
	EntityManager em;
	
	public Page<Wording> retrivePageWordingBySearchandSort(String engWord, String benWord, String engSynoname, String benSynoname, String memorised,String status,Pageable pageable) throws ParseException{
		CriteriaBuilder builder =  em.getCriteriaBuilder();
		CriteriaQuery<Wording> criteria = builder.createQuery(Wording.class);
		Root<Wording> supportItemRoot = criteria.from(Wording.class);
		 List<Predicate> predicates = new ArrayList<Predicate>();
		 
		
		
		if (!engWord.equals("")) {
			  predicates.add(builder.like(supportItemRoot.get("engWord"), engWord)); 
		}
		if (!benWord.equals("")) {
			  predicates.add(builder.equal(supportItemRoot.get("benWord"), benWord)); 
		}
		if (!engSynoname.equals("")) {
			  predicates.add(builder.like(supportItemRoot.get("engSynoname"), engSynoname)); 
		}
		if (!benSynoname.equals("")) {
			  predicates.add(builder.equal(supportItemRoot.get("benSynoname"),   benSynoname)); 
		}
		  
		if (!memorised.equals("ALL")) {
			  predicates.add(builder.equal(supportItemRoot.get("memorised"),   memorised)); 
		}
		if (!status.equals("ALL")) {
			  predicates.add(builder.equal(supportItemRoot.get("memorised"),   status)); 
		}
		  /*
		  if (!ticketCreatedDate.equals("")) {
			 
			  SimpleDateFormat myformatter = new SimpleDateFormat("yyyy-MM-dd");
			  java.util.Date date = myformatter.parse(ticketCreatedDate);
			  
			  predicates.add(builder.greaterThanOrEqualTo(supportItemRoot.get("ticketCreatedDate"), date));
		}*/
		  
			 
		 criteria.where(builder.and(predicates.toArray( new Predicate[predicates.size()])));
		 
		 

	        criteria.orderBy(builder.desc(supportItemRoot.get("seqOrder")));
	        List<Wording> result = em.createQuery(criteria).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
	        
	        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
	        Root<Wording> supportItemRootRootCount = countQuery.from(Wording.class);
	        countQuery.select(builder.count(supportItemRootRootCount)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

	        // Fetches the count of all SupItem as per given criteria
	        Long count = em.createQuery(countQuery).getSingleResult();

	        Page<Wording> result1 = new PageImpl<>(result, pageable, count);
	        return result1;

	}

}
